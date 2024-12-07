package fhv.team11.project.ems.user.service.validators;

import fhv.team11.project.ems.commons.validation.domain.IDomainValidator;
import fhv.team11.project.ems.commons.validation.domain.ValidatorFor;
import fhv.team11.project.ems.user.transfer.UserListDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

@Component
@ValidatorFor(UserListDTO.class)
public class UserListDTOValidator implements IDomainValidator<UserListDTO> {


    private final Validator validator;

    @Autowired
    public UserListDTOValidator(Validator validator){
        this.validator =validator;
    }

    @AllArgsConstructor
    @Getter
    private static class UserListValidation{

    }
    @Override
    public BindingResult validate(UserListDTO userListDTO) {
        return null;
    }
}
