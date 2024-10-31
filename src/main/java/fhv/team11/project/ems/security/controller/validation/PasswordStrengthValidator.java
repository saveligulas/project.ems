package fhv.team11.project.ems.security.controller.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordStrengthValidator implements ConstraintValidator<PasswordConstraint, String> {

    @Override
    public void initialize(PasswordConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.isBlank()) {
            return false;
        }

        if (s.length() < 8) {
            return false;
        }

        if (s.contains(" ")) {
            return false;
        }

        if (!s.matches(".*[0-9].*")) {
            return false;
        }

        if (!s.matches(".*[A-Z].*")) {
            return false;
        }

        return true;
    }
}
