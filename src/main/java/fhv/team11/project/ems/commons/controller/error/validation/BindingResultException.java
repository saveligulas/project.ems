package fhv.team11.project.ems.commons.controller.error.validation;

import fhv.team11.project.ems.commons.controller.error.RedirectionException;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class BindingResultException extends RedirectionException {
    private final BindingResult bindingResult;
    private final String modelAttributeName;

    public BindingResultException(BindingResult bindingResult, String modelAttributeName, String redirectEndpoint) {
        super(redirectEndpoint);
        this.bindingResult = bindingResult;
        this.modelAttributeName = modelAttributeName;
    }

    public BindingResultException(BindingResult bindingResult, String modelAttributeName, String redirectEndpoint, String exceptionEndpoint) {
        super(exceptionEndpoint, redirectEndpoint);
        this.bindingResult = bindingResult;
        this.modelAttributeName = modelAttributeName;
    }

}
