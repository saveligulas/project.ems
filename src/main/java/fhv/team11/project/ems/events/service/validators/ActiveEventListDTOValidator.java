package fhv.team11.project.ems.events.service.validators;

import fhv.team11.project.ems.commons.validation.domain.IDomainValidator;
import fhv.team11.project.ems.commons.validation.domain.ValidatorFor;
import fhv.team11.project.ems.events.transfer.ActiveEventListDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;


@Component
@ValidatorFor(ActiveEventListDTO.class)
public class ActiveEventListDTOValidator implements IDomainValidator<ActiveEventListDTO> {

    private final Validator validator;

    @Autowired
    public ActiveEventListDTOValidator(Validator validator){
        this.validator = validator;
    }
    @AllArgsConstructor
    @Getter
    private static class ActiveEventListValidation{

    }
    @Override
    public BindingResult validate(ActiveEventListDTO activeEventListDTO) {
        return null;
    }
}
