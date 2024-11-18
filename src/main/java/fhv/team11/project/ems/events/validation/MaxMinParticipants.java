package fhv.team11.project.ems.events.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalTime;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MaxMinParticipantsValidation.class)
public @interface MaxMinParticipants {
    String message() default "Max participants should be higher than min participants";
    String min();
    String max();
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
