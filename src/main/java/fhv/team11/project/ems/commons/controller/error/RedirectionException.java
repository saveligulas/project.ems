package fhv.team11.project.ems.commons.controller.error;

import lombok.Getter;

@Getter
public class RedirectionException extends RuntimeException {
    private final String exceptionEndpoint;
    private final String redirectEndpoint;

    public RedirectionException() {
        this("Unexpected Error occurred");
    }

    public RedirectionException(String message) {
        this(message, "error");
    }

    public RedirectionException(String message, String redirectEndpoint) {
        this(message, redirectEndpoint, "unspecified");
    }

    public RedirectionException(String message, String redirectEndpoint, String exceptionEndpoint) {
        super(message);
        this.exceptionEndpoint = exceptionEndpoint;
        this.redirectEndpoint = redirectEndpoint;
    }
}
