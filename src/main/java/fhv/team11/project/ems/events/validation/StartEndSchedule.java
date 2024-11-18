package fhv.team11.project.ems.events.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.cglib.core.Local;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalTime;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StartEndScheduleValidation.class)
public @interface StartEndSchedule {
    String message() default "The Starting time should not be after the ending time";
    String min();
    String max();
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
