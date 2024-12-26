package fhv.team11.project.ems.commons.controller.error;

import lombok.Getter;

import java.util.List;

@Getter
//TODO add errorMessages List of strings to the redirectattributes to handle in the frontend
public class RedirectionException extends RuntimeException {
    private final String exceptionEndpoint;
    private final String redirectEndpoint;
    private final List<String> errorMessages;

    public RedirectionException() {
        this("Unexpected Error occurred");
    }

    public RedirectionException(String message) {
        this(message, "error");
    }

    public RedirectionException(String message, String redirectEndpoint) {
        this(message, redirectEndpoint, List.of());
    }

    public RedirectionException(String message, String redirectEndpoint, List<String> errorMessages) {
        this(message, redirectEndpoint, errorMessages, "unspecified");
    }

    public RedirectionException(String message, String redirectEndpoint, List<String> errorMessages, String exceptionEndpoint) {
        super(message);
        this.exceptionEndpoint = exceptionEndpoint;
        this.redirectEndpoint = redirectEndpoint;
        this.errorMessages = errorMessages;
    }
}
