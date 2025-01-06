package fhv.team11.project.ems.events.transfer;

import fhv.team11.project.ems.events.repo.EventCategory;
import lombok.Data;

@Data
public class EventTemplateListView {
    private Long id;
    private String name;
    private EventCategory category;
    private Integer plannedEventCount;
}
