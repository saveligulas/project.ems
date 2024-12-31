package fhv.team11.project.ems.events.service.validators;

import fhv.team11.project.ems.commons.validation.domain.IDomainValidator;
import fhv.team11.project.ems.commons.validation.domain.ValidatorFor;
import fhv.team11.project.ems.events.transfer.EventDateDTO;
import fhv.team11.project.ems.events.transfer.EventWizard;
import fhv.team11.project.ems.events.validation.DateDifference;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.util.TreeSet;

@Component
@ValidatorFor(EventWizard.class)
public class ActiveEventWizardDTOValidator implements IDomainValidator<EventWizard> {
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
        private TreeSet<EventDateDTO> activeEventDates = new TreeSet<>();
    }
    @Override
    public BindingResult validate(EventWizard eventWizard) {
        return null;
    }
}
