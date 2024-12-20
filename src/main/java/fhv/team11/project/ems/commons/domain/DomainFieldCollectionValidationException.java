package fhv.team11.project.ems.commons.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DomainFieldCollectionValidationException extends DomainException{
    private final Map<List<String>, String> fieldErrors;

    public DomainFieldCollectionValidationException(Map<List<String>, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public DomainFieldCollectionValidationException(List<String> fieldNames, String errorMessage) {
        this(Map.of(fieldNames, errorMessage));
    }
}
