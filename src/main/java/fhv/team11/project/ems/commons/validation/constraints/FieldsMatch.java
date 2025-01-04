package fhv.team11.project.ems.commons.validation.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = FieldsMatchValidator.class)
public @interface FieldsMatch {
    String fieldOne();
    String fieldTwo();
    String message() default "Fields do not match.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
