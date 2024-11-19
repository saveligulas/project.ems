package fhv.team11.project.ems.security.transfer.domain.error;

import fhv.team11.project.ems.commons.validation.domain.error.BindingResultException;
import org.springframework.validation.BindingResult;

public class RegisterRequestValidationException extends BindingResultException {
    public RegisterRequestValidationException(BindingResult bindingResult) {
        super(bindingResult, "registerRequest", "register");
    }
}
