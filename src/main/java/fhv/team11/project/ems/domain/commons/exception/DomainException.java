package fhv.team11.project.ems.domain.commons.exception;

public class DomainException extends Exception {
    public DomainException() {
    }

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
