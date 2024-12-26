package fhv.team11.project.ems.domain.commons.exception;

import fhv.team11.project.ems.commons.validation.domain.IValidationException;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DomainValidationException extends DomainException implements IValidationException {
    private final Map<String, String> fieldErrors;
    private final List<String> errorMessages;

    public DomainValidationException(Map<String, String> fieldErrors, List<String> errorMessages) {
        this.fieldErrors = Collections.unmodifiableMap(fieldErrors);
        this.errorMessages = Collections.unmodifiableList(errorMessages);
    }

    @Override
    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    @Override
    public List<String> getErrorMessages() {
        return errorMessages;
    }
}
