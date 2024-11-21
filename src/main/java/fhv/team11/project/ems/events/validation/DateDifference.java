package fhv.team11.project.ems.events.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateDifferenceValidation.class)
public @interface DateDifference {
    String message() default "The date difference should lower than {days}";
    long days() default 90;
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
