package fhv.team11.project.ems.security.error;

import fhv.team11.project.ems.commons.validation.domain.error.BindingResultException;
import fhv.team11.project.ems.commons.validation.error.SimpleValidationException;
import org.springframework.validation.BindingResult;

import java.util.List;

public class RegistrationException extends SimpleValidationException {

    public RegistrationException(String field, String message) {
        super(field, message);
    }
}
