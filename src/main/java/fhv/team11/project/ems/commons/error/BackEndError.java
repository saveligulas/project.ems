package fhv.team11.project.ems.commons.error;

public class BackEndError extends RuntimeException {
    public BackEndError(String message) {
        super(message);
    }
}
