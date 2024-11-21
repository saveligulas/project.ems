package fhv.team11.project.ems.events.transfer;


import fhv.team11.project.ems.events.validation.StartEndSchedule;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;

@Data
@StartEndSchedule(min = "startTime",max = "endTime",message = "Start time must be before end time")
public class ScheduleEventDTO {
    @NotNull(message = "Please set a start time")
    private LocalTime startTime;
    @NotNull(message = "Please set an end time")
    private LocalTime endTime;

}
