package fhv.team11.project.ems.domain.commons;

public class EmailValidator {
    public static boolean isValid(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        int atIndex = email.indexOf('@');
        if (atIndex <= 0 || email.indexOf('@', atIndex + 1) != -1) {
            return false;
        }

        String[] parts = email.split("@");
        String localPart = parts[0];
        String domain = parts[1];

        if (localPart.length() > 64) {
            return false;
        }

        if (domain.length() > 255) {
            return false;
        }

        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
}
