package fhv.team11.project.ems.events.transfer;

import fhv.team11.project.ems.commons.validation.model.IModelAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data
public class EventDateDTO implements IModelAttribute {
    private LocalDate date;
    private String name;
    private EventScheduleDTO eventScheduleDTO = new EventScheduleDTO();
}
