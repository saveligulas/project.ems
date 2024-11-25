package fhv.team11.project.ems.events.transfer;

import fhv.team11.project.ems.events.validation.DateDifference;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.TreeSet;

@Data
public class ActiveEventWizardDTO {
    @NotEmpty(message = "Die Liste der Termine darf nicht leer sein.")
    @DateDifference(message = "Die Termine dürfen nicht mehr als {days} Tage auseinander liegen.", days = 90)
    private TreeSet<ActiveEventDateDTO> activeEventDates = new TreeSet<>();
    private ScheduleEventDTO scheduleEvent;

    public void addActiveEvent(ActiveEventDateDTO activeEventDateDTO) {
        activeEventDates.add(activeEventDateDTO);
    }

}
