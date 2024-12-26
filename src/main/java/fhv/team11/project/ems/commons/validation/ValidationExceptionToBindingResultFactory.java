package fhv.team11.project.ems.commons.validation;

import fhv.team11.project.ems.commons.validation.domain.BeanPropertyBindingResultBuilder;
import fhv.team11.project.ems.commons.validation.domain.IValidationException;
import fhv.team11.project.ems.commons.validation.domain.error.BindingResultException;
import fhv.team11.project.ems.commons.validation.error.SimpleValidationException;
import fhv.team11.project.ems.commons.validation.model.IModelAttribute;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import org.springframework.validation.BindingResult;

import java.util.Map;

public class ValidationExceptionToBindingResultFactory {
    public static void handle(IValidationException e, IModelAttribute modelAttribute, String redirectEndpoint) {
        throw new BindingResultException(buildBindingResult(modelAttribute, e.getFieldErrors()), modelAttribute.getModelAttributeName(), redirectEndpoint);
    }

    private static BindingResult buildBindingResult(IModelAttribute modelAttribute, String fieldName, String errorMessage) {
        return buildBindingResult(modelAttribute, Map.of(fieldName, errorMessage));
    }

    private static BindingResult buildBindingResult(IModelAttribute modelAttribute, Map<String, String> fieldErrorMessages) {
        BeanPropertyBindingResultBuilder bindingResultBuilder = new BeanPropertyBindingResultBuilder(modelAttribute);
        bindingResultBuilder.addRejectValues(fieldErrorMessages);
        return bindingResultBuilder.build();
    }
}
