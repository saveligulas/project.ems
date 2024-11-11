package fhv.team11.project.ems.commons.error;

public class ReadWriteException extends DatabaseException {
    public ReadWriteException(String message) {
        super(message);
    }
}
