package fhv.team11.project.ems.commons.controller.error;

public class UnauthorizedAccessException extends RedirectionException {
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
