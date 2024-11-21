package fhv.team11.project.ems.security.transfer.domain.error;

import fhv.team11.project.ems.commons.validation.domain.BeanPropertyBindingResultBuilder;
import fhv.team11.project.ems.commons.validation.domain.error.BindingResultException;
import fhv.team11.project.ems.security.jwt.JwtSecurityContextHolder;
import fhv.team11.project.ems.security.transfer.AuthenticationRequest;
import fhv.team11.project.ems.security.transfer.RegisterRequest;
import org.springframework.validation.BindingResult;

public class AuthenticationRequestValidationException extends RuntimeException {
    public AuthenticationRequestValidationException() {
        super();
    }

    public AuthenticationRequestValidationException(String message) {
        super(message);
    }
}
