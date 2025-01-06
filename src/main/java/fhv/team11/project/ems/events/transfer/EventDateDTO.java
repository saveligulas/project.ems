package fhv.team11.project.ems.events.transfer;

import fhv.team11.project.ems.commons.validation.model.IModelAttribute;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventDateDTO implements IModelAttribute {
    private LocalDate date;
    private String name;
    private EventScheduleDTO eventSchedule = new EventScheduleDTO();
}
