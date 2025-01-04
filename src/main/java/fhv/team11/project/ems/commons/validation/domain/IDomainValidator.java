package fhv.team11.project.ems.commons.validation.domain;

import fhv.team11.project.ems.commons.validation.model.IModelAttribute;
import fhv.team11.project.ems.commons.validation.model.IModelAttributeName;
import jakarta.validation.ConstraintViolation;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.Set;

public interface IDomainValidator<DTO extends IModelAttribute> {
    BindingResult validate(DTO dto);

    default BindingResult buildBindingResult(DTO dto) {
        return new BeanPropertyBindingResult(dto, dto.getModelAttributeName());
    }

    @Deprecated
    default void addToBindingResult(Set<ConstraintViolation<?>> violations, BindingResult bindingResult) {
        violations.forEach(violation -> {
            String property = violation.getPropertyPath().toString();
            bindingResult.rejectValue(property,
                    "invalid." + property,
                    violation.getMessage());
        });
    }
}
