package fhv.team11.project.ems.commons.validation.error;

import fhv.team11.project.ems.commons.validation.domain.IValidationException;
import lombok.Getter;

import java.util.List;
import java.util.Map;

public class SimpleValidationException extends Exception implements IValidationException {
    private final String field;
    private final String message;

    public SimpleValidationException(String field, String message) {
        this.field = field;
        this.message = message;
    }

    @Override
    public Map<String, String> getFieldErrors() {
        return Map.of(field, message);
    }

    @Override
    public List<String> getErrorMessages() {
        return List.of();
    }
}
