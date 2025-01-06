package fhv.team11.project.ems.commons.services;

public interface IHandleSearchQueries {
    default Integer tryParseInt(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
