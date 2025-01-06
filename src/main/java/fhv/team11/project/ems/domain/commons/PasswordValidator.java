package fhv.team11.project.ems.domain.commons;


public class PasswordValidator {
    public static boolean isValid(String password) {
        if (password == null || password.isBlank()) {
            return false;
        }

        if (password.length() < 8) {
            return false;
        }

        if (password.contains(" ")) {
            return false;
        }

        if (!password.matches(".*[0-9].*")) {
            return false;
        }

        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        return true;
    }
}
