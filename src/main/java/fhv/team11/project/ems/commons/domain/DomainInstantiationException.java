package fhv.team11.project.ems.commons.domain;

import java.util.Map;

public class DomainInstantiationException extends DomainFieldValidationException {
    public DomainInstantiationException(Map<String, String> fieldErrors) {
        super(fieldErrors);
    }
}
