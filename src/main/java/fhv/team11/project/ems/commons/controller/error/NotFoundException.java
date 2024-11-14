package fhv.team11.project.ems.commons.controller.error;

public class NotFoundException extends RedirectionException {
    public NotFoundException(String message) {
        super(message);
    }
}
