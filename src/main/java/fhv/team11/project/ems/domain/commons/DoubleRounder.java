package fhv.team11.project.ems.domain.commons;

public class DoubleRounder {
    private static final int DEFAULT_DECIMAL_PLACES = 2;

    // For primitive double
    public static double round(double value) {
        return round(value, DEFAULT_DECIMAL_PLACES);
    }

    public static double round(double value, int decimalPlaces) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            return value;
        }
        double scale = Math.pow(10, decimalPlaces);
        return Math.round(value * scale) / scale;
    }

    // For Double wrapper
    public static Double round(Double value) {
        if (value == null) {
            return null;
        }
        return round(value.doubleValue());
    }

    public static Double round(Double value, int decimalPlaces) {
        if (value == null) {
            return null;
        }
        return round(value.doubleValue(), decimalPlaces);
    }

    // Format to String with exactly 2 decimal places
    public static String formatTwoDecimals(double value) {
        return String.format("%.2f", round(value));
    }

    public static String formatTwoDecimals(Double value) {
        if (value == null) {
            return null;
        }
        return formatTwoDecimals(value.doubleValue());
    }
}
