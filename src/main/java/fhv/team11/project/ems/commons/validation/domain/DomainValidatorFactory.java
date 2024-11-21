package fhv.team11.project.ems.commons.validation.domain;

import fhv.team11.project.ems.commons.validation.model.IModelAttribute;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DomainValidatorFactory {
    private final ApplicationContext applicationContext;

    public DomainValidatorFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @SuppressWarnings("unchecked")
    public <T extends IModelAttribute> IDomainValidator<T> getValidator(Class<T> dtoClass) {
        Map<String, Object> validators = applicationContext.getBeansWithAnnotation(ValidatorFor.class);

        return validators.values().stream()
                .filter(validator -> {
                    ValidatorFor annotation = validator.getClass().getAnnotation(ValidatorFor.class);
                    return annotation.value().equals(dtoClass);
                })
                .map(validator -> (IDomainValidator<T>) validator)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No validator found for: " + dtoClass.getSimpleName()));
    }
}
