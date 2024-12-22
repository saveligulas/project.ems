package fhv.team11.project.ems.domain.commons.exception;

import java.util.List;
import java.util.Map;

public class DomainValidationException extends DomainException {
    private final Map<String, String> fieldErrors;
    private final List<String> errorMessages;

    public DomainValidationException(Map<String, String> fieldErrors, List<String> errorMessages) {
        this.fieldErrors = fieldErrors;
        this.errorMessages = errorMessages;
    }
}
