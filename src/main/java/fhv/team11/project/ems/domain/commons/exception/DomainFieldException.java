package fhv.team11.project.ems.domain.commons.exception;

import java.util.List;
import java.util.Map;

public class DomainFieldException extends DomainValidationException {
    private final String fieldName;
    private final String errorMessage;

    public DomainFieldException(String fieldName, String errorMessage) {
        super(Map.of(fieldName, errorMessage), List.of());
        this.fieldName = fieldName;
        this.errorMessage = errorMessage;
    }
}
