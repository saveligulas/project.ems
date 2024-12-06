package fhv.team11.project.ems.events.service.validators;

import fhv.team11.project.ems.commons.validation.domain.IDomainValidator;
import fhv.team11.project.ems.commons.validation.domain.ValidatorFor;
import fhv.team11.project.ems.events.transfer.ActiveEventDateDTO;
import fhv.team11.project.ems.events.transfer.ActiveEventWizardDTO;
import fhv.team11.project.ems.events.validation.DateDifference;
import fhv.team11.project.ems.events.validation.DateDifferenceValidation;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.util.TreeSet;

@Component
@ValidatorFor(ActiveEventWizardDTO.class)
public class ActiveEventWizardDTOValidator implements IDomainValidator<ActiveEventWizardDTO> {
    private final Validator validator;

    @Autowired
    public ActiveEventWizardDTOValidator(Validator validator){
        this.validator = validator;
    }

    @AllArgsConstructor
    @Getter
    private static class ActiveEventWizardValidation{
        @NotEmpty(message = "Die Liste der Termine darf nicht leer sein.")
        @DateDifference(message = "Die Termine dürfen nicht mehr als {days} Tage auseinander liegen.", days = 90)
        private TreeSet<ActiveEventDateDTO> activeEventDates = new TreeSet<>();
    }
    @Override
    public BindingResult validate(ActiveEventWizardDTO activeEventWizardDTO) {
        BindingResult bindingResult = buildBindingResult(activeEventWizardDTO);
        validator.validate(new ActiveEventWizardValidation(activeEventWizardDTO.getActiveEventDates()),
                bindingResult);
        return bindingResult;
    }
}
