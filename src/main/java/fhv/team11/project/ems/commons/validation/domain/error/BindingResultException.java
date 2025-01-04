package fhv.team11.project.ems.commons.validation.domain.error;

import fhv.team11.project.ems.commons.controller.error.RedirectionException;
import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.util.List;

@Getter
public class BindingResultException extends RedirectionException {
    private final BindingResult bindingResult;
    private final String modelAttributeName;

    public BindingResultException(BindingResult bindingResult, String modelAttributeName, String redirectEndpoint) {
        this(bindingResult, modelAttributeName, redirectEndpoint, List.of());
    }

    public BindingResultException(BindingResult bindingResult, String modelAttributeName, String redirectEndpoint, List<String> errorMessages) {
        this(bindingResult, modelAttributeName, redirectEndpoint, errorMessages, "unspecified");
    }

    public BindingResultException(BindingResult bindingResult, String modelAttributeName, String redirectEndpoint, List<String> errorMessages, String exceptionEndpoint) {
        super("Binding Error Occurred", redirectEndpoint, errorMessages, exceptionEndpoint);
        this.bindingResult = bindingResult;
        this.modelAttributeName = modelAttributeName;
    }
}
