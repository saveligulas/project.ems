package fhv.team11.project.ems.security.error;

public class RegistrationError extends RuntimeException {
    public RegistrationError(String message) {
        super(message);
    }
}
