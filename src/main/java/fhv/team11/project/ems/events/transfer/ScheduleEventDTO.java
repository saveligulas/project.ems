package fhv.team11.project.ems.events.transfer;


import lombok.Data;

import java.time.LocalTime;

@Data
public class ScheduleEventDTO {
    private LocalTime startTime;
    private LocalTime endTime;

}
