package fhv.team11.project.ems.events.transfer;

import fhv.team11.project.ems.commons.validation.model.IModelAttribute;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.TreeSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveEventView implements IModelAttribute {
    private EventTemplateView eventTemplateView;
    private List<EventDateDTO> eventDates;
    private int bookedPlaces;
}
