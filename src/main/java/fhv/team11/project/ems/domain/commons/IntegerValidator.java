package fhv.team11.project.ems.domain.commons;

public class IntegerValidator {
    public static boolean isValid(Integer value) {
        return Validator.isNotNull(value);
    }

    public static boolean isValid(Integer value, int min) {
        if (!isValid(value)) {
            return false;
        }

        if (value < min) {
            return false;
        }

        return true;
    }

    public static boolean isValid(Integer value, int min, int max) {
        if (!isValid(value)) {
            return false;
        }

        if (value < min || value > max) {
            return false;
        }

        return true;
    }
}
