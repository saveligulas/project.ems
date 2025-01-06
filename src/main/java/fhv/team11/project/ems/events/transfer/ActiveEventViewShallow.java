package fhv.team11.project.ems.events.transfer;

import lombok.Data;

import java.util.List;

@Data
public class ActiveEventViewShallow {
    private List<EventDateDTO> eventDates;
}
