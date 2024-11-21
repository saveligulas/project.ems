package fhv.team11.project.ems.security.error;

import fhv.team11.project.ems.commons.controller.error.RedirectionException;

public class SecuredEndpointAccessException extends RedirectionException {
    public SecuredEndpointAccessException(String message) {
        super(message);
    }

    public SecuredEndpointAccessException() {
        super("You need to be logged in to perform this action");
    }
}
