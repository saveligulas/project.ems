package fhv.team11.project.ems.security.error;

public class RegistrationError extends RuntimeException {
    public RegistrationError(String message) {
        super(message);
    }
    public RegistrationError() {
        super("Registration failed. Please make sure to fill in all required fields and try again.");
    }
}
