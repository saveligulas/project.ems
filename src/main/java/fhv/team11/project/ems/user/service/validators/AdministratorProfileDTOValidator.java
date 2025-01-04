package fhv.team11.project.ems.user.service.validators;

import fhv.team11.project.ems.commons.validation.domain.IDomainValidator;
import fhv.team11.project.ems.commons.validation.domain.ValidatorFor;
import fhv.team11.project.ems.user.transfer.AdministratorProfileDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

@Component
@ValidatorFor(AdministratorProfileDTO.class)
public class AdministratorProfileDTOValidator implements IDomainValidator<AdministratorProfileDTO> {

    private final Validator validator;

    @Autowired
    public AdministratorProfileDTOValidator(Validator validator){
        this.validator = validator;
    }
    @AllArgsConstructor
    @Getter
    private static class AdministratorProfileValidation{

    }
    @Override
    public BindingResult validate(AdministratorProfileDTO administratorProfileDTO) {
        return null;
    }
}
