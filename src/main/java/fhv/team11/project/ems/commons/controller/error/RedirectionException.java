package fhv.team11.project.ems.commons.controller.error;

import lombok.Getter;

@Getter
public class RedirectionException extends RuntimeException {
    private final String exceptionEndpoint;
    private final String redirectEndpoint;

    public RedirectionException() {
        this("unspecified", "error");
    }

    public RedirectionException(String redirectEndpoint) {
        this("unspecified", redirectEndpoint);
    }

    public RedirectionException(String exceptionEndpoint, String redirectEndpoint) {
        super();
        this.exceptionEndpoint = exceptionEndpoint;
        this.redirectEndpoint = redirectEndpoint;
    }
}
