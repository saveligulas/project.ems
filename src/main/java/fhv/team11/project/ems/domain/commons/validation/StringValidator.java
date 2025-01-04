package fhv.team11.project.ems.domain.commons.validation;

public class StringValidator {
    public static boolean isValid(String s) {
        return s != null && !s.isEmpty();
    }

    public static boolean isValid(String s, int min) {
        if (!isValid(s)) {
            return false;
        }

        if (s.length() < min) {
            return false;
        }

        return true;
    }

    public static boolean isValid(String s, int min, int max) {
        if (!isValid(s)) {
            return false;
        }

        if (s.length() < min || s.length() > max) {
            return false;
        }

        return true;
    }
}
