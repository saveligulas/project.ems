package fhv.team11.project.ems.commons.validation;

import fhv.team11.project.ems.commons.validation.domain.IValidationException;
import fhv.team11.project.ems.commons.validation.error.SimpleValidationException;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import jakarta.validation.ValidationException;

import java.util.HashMap;
import java.util.Map;

public class ValidationExceptionDecorator {
    public static void encapsulateFieldErrorsTo(String fieldName, IValidationException e) throws DomainValidationException {
        Map<String, String> encapsulateFieldErrors = new HashMap<>();
        for (Map.Entry<String, String> entry : e.getFieldErrors().entrySet()) {
            encapsulateFieldErrors.put(fieldName + "." + entry.getKey(), entry.getValue());
        }
        throw new DomainValidationException(encapsulateFieldErrors, e.getErrorMessages());
    }
}
