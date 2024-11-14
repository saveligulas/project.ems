package fhv.team11.project.ems.security.jwt.domain.error;

public class AuthenticationRequestValidationException extends RuntimeException {
    public AuthenticationRequestValidationException(String message) {
        super(message);
    }
}
