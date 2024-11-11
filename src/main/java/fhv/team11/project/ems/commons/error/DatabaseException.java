package fhv.team11.project.ems.commons.error;

public class DatabaseException extends Exception {
    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException() {
    }
}
