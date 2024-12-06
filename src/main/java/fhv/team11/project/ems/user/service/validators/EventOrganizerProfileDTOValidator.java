package fhv.team11.project.ems.user.service.validators;

import fhv.team11.project.ems.commons.validation.domain.IDomainValidator;
import fhv.team11.project.ems.commons.validation.domain.ValidatorFor;
import fhv.team11.project.ems.user.transfer.EventOrganizerProfileDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;


@Component
@ValidatorFor(EventOrganizerProfileDTO.class)
public class EventOrganizerProfileDTOValidator implements IDomainValidator<EventOrganizerProfileDTO> {

    private final Validator validator;

    @Autowired
    public EventOrganizerProfileDTOValidator(Validator validator){
        this.validator = validator;
    }

    @AllArgsConstructor
    @Getter
    private static class EventOrganizerProfileValidation{

    }
    @Override
    public BindingResult validate(EventOrganizerProfileDTO eventOrganizerProfileDTO) {
        return null;
    }
}
