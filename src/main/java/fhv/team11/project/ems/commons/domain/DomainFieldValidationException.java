package fhv.team11.project.ems.commons.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DomainFieldValidationException extends DomainException {
    private final Map<String, String> fieldErrors;

    public DomainFieldValidationException(Map<String, String> fieldErrors) {
        super();
        this.fieldErrors = Collections.unmodifiableMap(fieldErrors);
    }

    public DomainFieldValidationException(String fieldName, String errorMessage) {
        this(new HashMap<>(Map.of(fieldName, errorMessage)));
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }
}
