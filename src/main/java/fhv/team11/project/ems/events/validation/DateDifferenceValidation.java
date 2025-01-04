package fhv.team11.project.ems.events.validation;

import fhv.team11.project.ems.events.transfer.EventDateDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.SortedSet;

public class DateDifferenceValidation implements ConstraintValidator <DateDifference, SortedSet<EventDateDTO>>{
    private long maxDays;

    @Override
    public void initialize(DateDifference dateDifference) {
        this.maxDays = dateDifference.days();
    }

    @Override
    public boolean isValid(SortedSet<EventDateDTO> activeEventDates, ConstraintValidatorContext context) {
        if (activeEventDates == null || activeEventDates.isEmpty()) {
            return true;
        }

        // Iterate through the sorted set to check date differences
        LocalDate previousDate = null;
        for (EventDateDTO eventDate : activeEventDates) {
            if (previousDate != null) {
                long daysBetween = ChronoUnit.DAYS.between(previousDate, eventDate.getDate());
                if (daysBetween > maxDays) {
                    return false;
                }
            }
            previousDate = eventDate.getDate();
        }

        return true;
    }
}
