package fhv.team11.project.ems.security.error;

public class AuthenticationErrorException extends RuntimeException {
    public AuthenticationErrorException(String message) {
        super(message);
    }

    public AuthenticationErrorException() {
        super("You need to be logged in to perform this action");
    }
}
