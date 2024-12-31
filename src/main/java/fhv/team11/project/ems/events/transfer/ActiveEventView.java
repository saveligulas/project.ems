package fhv.team11.project.ems.events.transfer;

import fhv.team11.project.ems.commons.validation.model.IModelAttribute;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.TreeSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveEventView implements IModelAttribute {

    @Valid
    private TreeSet<EventDateDTO> activeEventDates = new TreeSet<>();
    @Valid
    private EventScheduleDTO scheduleEvent;

    private Long templateId;
    @Valid
    private EventTemplateListDTO eventTemplateListDTO;
    @Valid
    private EventTemplateDTO eventTemplateDTO;
    @Valid
    private ActiveEventListDTO activeEventListDTO;
}
