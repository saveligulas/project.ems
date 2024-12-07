package fhv.team11.project.ems.events.service.validators;

import fhv.team11.project.ems.commons.validation.domain.IDomainValidator;
import fhv.team11.project.ems.commons.validation.domain.ValidatorFor;
import fhv.team11.project.ems.events.transfer.EventTemplateListDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

@Component
@ValidatorFor(EventTemplateListDTO.class)
public class EventTemplateListDTOValidator implements IDomainValidator<EventTemplateListDTO> {

    private final Validator validator;

    @Autowired
    public EventTemplateListDTOValidator(Validator validator){
        this.validator = validator;
    }

    @AllArgsConstructor
    @Getter
    private static class EventTemplateListValidation{

    }
    @Override
    public BindingResult validate(EventTemplateListDTO eventTemplateListDTO) {
        return null;
    }
}
