package fhv.team11.project.ems.security.error;

public class RegistrationWeakPasswordException extends RuntimeException {
    public RegistrationWeakPasswordException(String message) {
        super(message);
    }
}
