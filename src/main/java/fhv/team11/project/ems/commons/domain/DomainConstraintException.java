package fhv.team11.project.ems.commons.domain;

public class DomainConstraintException extends DomainValidationException {
    public DomainConstraintException(String message, String field) {
        super(message, field);
    }
}
