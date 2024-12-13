package fhv.team11.project.ems.commons.domain;

public class DomainValidationException extends DomainException {
    private final String field;

    public DomainValidationException(String message, String field) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return this.field;
    }
}
