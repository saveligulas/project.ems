package fhv.team11.project.ems.events.service.validators;

import fhv.team11.project.ems.commons.validation.domain.IDomainValidator;
import fhv.team11.project.ems.commons.validation.domain.ValidatorFor;
import fhv.team11.project.ems.events.transfer.ActiveEventDateDTO;
import fhv.team11.project.ems.events.validation.MaxDate;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
@ValidatorFor(ActiveEventDateDTO.class)
public class ActiveEventDateDTOValidator implements IDomainValidator<ActiveEventDateDTO> {
    private final Validator validator;

    @Autowired
    public ActiveEventDateDTOValidator(Validator validator){
        this.validator = validator;
    }
    @AllArgsConstructor
    @Getter
    private static class ActiveEventDateValidation{
        @FutureOrPresent(message = "Date cannot be in the past")
        @MaxDate
        @NotNull(message = "Set a Date")
        private LocalDate date;

        private String name;


    }
    @Override
    public BindingResult validate(ActiveEventDateDTO activeEventDateDTO) {
        BindingResult bindingResult = buildBindingResult(activeEventDateDTO);
        validator.validate(new ActiveEventDateValidation(activeEventDateDTO.getDate(),
                activeEventDateDTO.getName()),bindingResult);
        return bindingResult;
    }
}
