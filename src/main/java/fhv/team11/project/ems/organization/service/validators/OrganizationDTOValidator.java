package fhv.team11.project.ems.organization.service.validators;

import fhv.team11.project.ems.commons.validation.domain.IDomainValidator;
import fhv.team11.project.ems.commons.validation.domain.ValidatorFor;
import fhv.team11.project.ems.organization.transfer.OrganizationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;


@Component
@ValidatorFor(OrganizationDTO.class)
public class OrganizationDTOValidator implements IDomainValidator<OrganizationDTO> {

    private final Validator validator;

    @Autowired
    public OrganizationDTOValidator(Validator validator){
        this.validator = validator;
    }
    @AllArgsConstructor
    @Getter
    private static class OrganizationValidation{

    }

    @Override
    public BindingResult validate(OrganizationDTO organizationDTO) {
        return null;
    }
}
