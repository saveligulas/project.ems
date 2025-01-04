package fhv.team11.project.ems.security.controller.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = PasswordStrengthValidator.class)
public @interface PasswordConstraint {
    String message() default "Password must contain at minimum:\n- 8 letters\n- 1 uppercase letter\n- 1 digit";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
