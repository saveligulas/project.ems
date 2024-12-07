package fhv.team11.project.ems.events.transfer;


import fhv.team11.project.ems.commons.validation.model.IModelAttribute;
import fhv.team11.project.ems.events.validation.StartEndSchedule;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;

@Data
public class ScheduleEventDTO implements IModelAttribute {
    private LocalTime startTime;
    private LocalTime endTime;

}
