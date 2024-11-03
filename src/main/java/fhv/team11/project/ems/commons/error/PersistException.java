package fhv.team11.project.ems.commons.error;

public class PersistException extends ReadWriteException{
    public PersistException(String message) {
        super(message);
    }
    public PersistException() {
        super("Entity is null or has an ID");
    }
}
