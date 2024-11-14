package fhv.team11.project.ems.security.jwt.domain.error;

import fhv.team11.project.ems.commons.validation.domain.IDomainValidator;
import fhv.team11.project.ems.commons.validation.domain.ValidatorFor;
import fhv.team11.project.ems.security.controller.validation.PasswordConstraint;
import fhv.team11.project.ems.security.transfer.RegisterRequest;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

@Component
@ValidatorFor(RegisterRequest.class)
public class RegisterRequestValidator implements IDomainValidator<RegisterRequest> {

    private final Validator validator;

    @Autowired
    public RegisterRequestValidator(Validator validator) {
        this.validator = validator;
    }

    @AllArgsConstructor
    private class RegisterRequestValidation {
        @PasswordConstraint
        @NotNull
        private String password;
    }

    @Override
    public BindingResult validate(RegisterRequest registerRequest) {
        BindingResult bindingResult = buildBindingResult(registerRequest);
        validator.validate(new RegisterRequestValidation(registerRequest.getPassword()), bindingResult);
        return bindingResult;
    }
}
