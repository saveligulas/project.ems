package fhv.team11.project.ems.user.service.validators;

import fhv.team11.project.ems.commons.validation.domain.IDomainValidator;
import fhv.team11.project.ems.commons.validation.domain.ValidatorFor;
import fhv.team11.project.ems.user.transfer.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;


@Component
@ValidatorFor(UserDTO.class)
public class UserDTOValidator implements IDomainValidator<UserDTO> {

    private final Validator validator;

    @Autowired
    public UserDTOValidator(Validator validator){
        this.validator = validator;
    }

    @AllArgsConstructor
    @Getter
    private static class UserValidation{

    }
    @Override
    public BindingResult validate(UserDTO userDTO) {
        return null;
    }
}
