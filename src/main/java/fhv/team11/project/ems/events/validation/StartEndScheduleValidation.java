package fhv.team11.project.ems.events.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.time.LocalTime;


public class StartEndScheduleValidation implements ConstraintValidator<StartEndSchedule, Object> {
    private String min;
    private String max;

    @Override
    public void initialize(StartEndSchedule startEndSchedule){
        this.min = startEndSchedule.min();
        this.max = startEndSchedule.max();
    }
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        try {

            Field minFieldObject = o.getClass().getDeclaredField(min);
            Field maxFieldObject = o.getClass().getDeclaredField(max);

            minFieldObject.setAccessible(true);
            maxFieldObject.setAccessible(true);


            Object minValue = minFieldObject.get(o);
            Object maxValue = maxFieldObject.get(o);

            if (minValue == null || maxValue == null) {
                return true;
            }

            if (minValue instanceof LocalTime && maxValue instanceof LocalTime) {
                LocalTime minTime = (LocalTime) minValue;
                LocalTime maxTime = (LocalTime) maxValue;

                return maxTime.isAfter(minTime);
            }

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return false;
    }
}
