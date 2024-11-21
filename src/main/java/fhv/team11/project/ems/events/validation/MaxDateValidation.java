package fhv.team11.project.ems.events.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class MaxDateValidation implements ConstraintValidator <MaxDate, LocalDate>{
    @Override
    public void initialize(MaxDate maxDate){}
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if (localDate == null) {
            return true;
        }

        LocalDate oneYearFromNow = LocalDate.now().plusYears(1);

        return !localDate.isAfter(oneYearFromNow);
    }
}
