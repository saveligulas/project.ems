package fhv.team11.project.ems.commons.home.base.attribute;

import lombok.Data;

@Data
public class EventAttributes {
    private final String eventRoot;

    public EventAttributes() {
        this.eventRoot = "Event Template";
    }
}
