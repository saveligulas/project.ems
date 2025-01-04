package fhv.team11.project.ems.organization.service.validators;

import fhv.team11.project.ems.commons.validation.domain.IDomainValidator;
import fhv.team11.project.ems.commons.validation.domain.ValidatorFor;
import fhv.team11.project.ems.organization.transfer.OrganizationListDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;


@Component
@ValidatorFor(OrganizationListDTO.class)
public class OrganizationListDTOValidator implements IDomainValidator<OrganizationListDTO> {

    private final Validator validator;

    @Autowired
    public OrganizationListDTOValidator(Validator validator){
        this.validator = validator;
    }
    @AllArgsConstructor
    @Getter
    private static class OrganizationListValidation{

    }
    @Override
    public BindingResult validate(OrganizationListDTO organizationListDTO) {
        return null;
    }

}
