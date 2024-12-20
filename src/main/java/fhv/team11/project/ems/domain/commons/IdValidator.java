package fhv.team11.project.ems.domain.commons;

public class IdValidator {
    public static boolean isValid(Long id) {
        if (id == null) {
            return false;
        }

        if (id < 0) {
            return false;
        }

        return true;
    }
}
