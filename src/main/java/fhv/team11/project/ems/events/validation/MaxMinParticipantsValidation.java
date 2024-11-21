package fhv.team11.project.ems.events.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;

public class MaxMinParticipantsValidation implements ConstraintValidator<MaxMinParticipants,Object> {
    private String min;
    private String max;

    @Override
    public void initialize(MaxMinParticipants minParticipants){
        this.min = minParticipants.min();
        this.max = minParticipants.max();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        try {
            Field minField = o.getClass().getDeclaredField(min);
            Field maxField = o.getClass().getDeclaredField(max);

            minField.setAccessible(true);
            maxField.setAccessible(true);

            Object minValue = minField.get(o);
            Object maxValue = maxField.get(o);

            if (minValue == null || maxValue == null) {
                return true; // Null values are valid, let @NotNull handle null checks
            }

            if (minValue instanceof Integer && maxValue instanceof Integer) {
                int minParticipants = (Integer) minValue;
                int maxParticipants = (Integer) maxValue;

                if (minParticipants < maxParticipants) {
                    return true;
                }
            }

            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                    "Die minimale Teilnehmeranzahl muss kleiner als die maximale sein"
            ).addConstraintViolation();
            return false;

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
        }
    }


