package fhv.team11.project.ems.security.error;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String email) {
        super("User with email " + email + " not found");
    }
}
