package fhv.team11.project.ems.domain.commons;

public class NumberValidator {
    // Integer validations
    public static boolean isValid(Integer value) {
        return value != null;
    }

    public static boolean isValid(int value, int min) {
        return value >= min;
    }

    public static boolean isValid(int value, int min, int max) {
        return value >= min && value <= max;
    }

    public static boolean isValid(Integer value, int min) {
        if (!isValid(value)) {
            return false;
        }
        return value >= min;
    }

    public static boolean isValid(Integer value, int min, int max) {
        if (!isValid(value)) {
            return false;
        }
        return value >= min && value <= max;
    }

    // Double validations
    public static boolean isValid(Double value) {
        return value != null && !value.isNaN() && !value.isInfinite();
    }

    public static boolean isValid(double value, double min) {
        return !Double.isNaN(value) && !Double.isInfinite(value) && value >= min;
    }

    public static boolean isValid(double value, double min, double max) {
        return !Double.isNaN(value) && !Double.isInfinite(value) && value >= min && value <= max;
    }

    public static boolean isValid(Double value, double min) {
        if (!isValid(value)) {
            return false;
        }
        return value >= min;
    }

    public static boolean isValid(Double value, double min, double max) {
        if (!isValid(value)) {
            return false;
        }
        return value >= min && value <= max;
    }
}
