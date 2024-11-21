package fhv.team11.project.ems.events.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MaxDateValidation.class)
public @interface MaxDate {
    String message() default "The date set should not surpass {days} in future";
    int days() default 365;
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
