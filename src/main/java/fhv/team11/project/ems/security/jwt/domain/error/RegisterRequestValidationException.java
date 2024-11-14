package fhv.team11.project.ems.security.jwt.domain.error;

import fhv.team11.project.ems.commons.controller.error.validation.BindingResultException;
import org.springframework.validation.BindingResult;

public class RegisterRequestValidationException extends BindingResultException {
    public RegisterRequestValidationException(BindingResult bindingResult, String redirectEndpoint) {
        super(bindingResult, "registerRequest", redirectEndpoint);
    }
}
