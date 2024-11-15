package fhv.team11.project.ems.commons.validation.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;

public class FieldsMatchValidator implements ConstraintValidator<FieldsMatch, Object> {

    private String fieldOneName;
    private String fieldTwoName;

    @Override
    public void initialize(FieldsMatch constraintAnnotation) {
        this.fieldOneName = constraintAnnotation.fieldOne();
        this.fieldTwoName = constraintAnnotation.fieldTwo();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (o == null) {
                return false;
            }

            Field fieldOne = o.getClass().getDeclaredField(fieldOneName);
            Field fieldTwo = o.getClass().getDeclaredField(fieldTwoName);

            fieldOne.setAccessible(true);
            fieldTwo.setAccessible(true);


            Object valueOne = fieldOne.get(o);
            Object valueTwo = fieldTwo.get(o);

            if (valueOne == null || valueTwo == null) {
                return false;
            }

            return valueOne.equals(valueTwo);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return false;
        }
    }
}
