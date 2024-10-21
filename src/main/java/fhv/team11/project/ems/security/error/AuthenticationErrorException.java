package fhv.team11.project.ems.security.error;

public class AuthenticationErrorException extends RuntimeException {
    public AuthenticationErrorException(String message) {
        super(message);
    }

    public AuthenticationErrorException() {
        super("An unexpected error occurred");
    }
}
