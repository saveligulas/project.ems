package fhv.team11.project.ems.security.transfer.domain.error;

public class AuthenticationRequestValidationException extends RuntimeException {
    public AuthenticationRequestValidationException(String message) {
        super(message);
    }
}
