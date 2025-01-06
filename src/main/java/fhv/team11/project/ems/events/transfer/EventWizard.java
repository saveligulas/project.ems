package fhv.team11.project.ems.events.transfer;

import fhv.team11.project.ems.commons.controller.IWizard;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

@Data
public class EventWizard implements IWizard {
    private List<EventDateDTO> eventDates = new ArrayList<>();

    public void addEventDate(EventDateDTO eventDateDTO) {
        eventDates.add(eventDateDTO);
    }
}
