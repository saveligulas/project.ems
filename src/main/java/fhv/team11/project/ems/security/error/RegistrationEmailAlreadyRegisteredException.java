package fhv.team11.project.ems.security.error;

public class RegistrationEmailAlreadyRegisteredException extends RegistrationError {
    public RegistrationEmailAlreadyRegisteredException() {
        super("User with email already registered");
    }
}
