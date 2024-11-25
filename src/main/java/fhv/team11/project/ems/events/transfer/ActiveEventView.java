package fhv.team11.project.ems.events.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.TreeSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveEventView {

    private TreeSet<ActiveEventDateDTO> activeEventDates = new TreeSet<>();
    private ScheduleEventDTO scheduleEvent;

    private Long templateId;
    private EventTemplateListDTO eventTemplateListDTO;
    private EventTemplateDTO eventTemplateDTO;
    private ActiveEventListDTO activeEventListDTO;
}
