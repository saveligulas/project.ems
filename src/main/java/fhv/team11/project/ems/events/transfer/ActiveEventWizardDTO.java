package fhv.team11.project.ems.events.transfer;

import lombok.Data;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.HashSet;
import java.util.Set;

@Data
public class ActiveEventWizardDTO {
    private Set<ActiveEventDateDTO> activeEventDates = new HashSet<>();
    private ScheduleEventDTO scheduleEvent;
    private Long templateId;

    public void addActiveEvent(ActiveEventDateDTO activeEventDateDTO) {
        activeEventDates.add(activeEventDateDTO);
    }
    public void setTemplate(Long templateId) {this.templateId = templateId;}
}
