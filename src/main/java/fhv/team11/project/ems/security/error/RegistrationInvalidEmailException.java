package fhv.team11.project.ems.security.error;

public class RegistrationInvalidEmailException extends RuntimeException {
    public RegistrationInvalidEmailException() {
        super("Invalid email address provided");
    }
}
