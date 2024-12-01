package fhv.team11.project.ems.user.service.validators;

import fhv.team11.project.ems.commons.validation.domain.IDomainValidator;
import fhv.team11.project.ems.commons.validation.domain.ValidatorFor;
import fhv.team11.project.ems.user.transfer.BackOfficeEmployeeProfileDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;


@Component
@ValidatorFor(BackOfficeEmployeeProfileDTO.class)
public class BackOfficeEmployeeProfileDTOValidator implements IDomainValidator<BackOfficeEmployeeProfileDTO> {

    private final Validator validator;

    @Autowired
    public BackOfficeEmployeeProfileDTOValidator(Validator validator){
        this.validator = validator;
    }
    @AllArgsConstructor
    @Getter
    private static class BackOfficeEmployeeProfileValidation{

    }

    @Override
    public BindingResult validate(BackOfficeEmployeeProfileDTO backOfficeEmployeeProfileDTO) {
        return null;
    }
}
