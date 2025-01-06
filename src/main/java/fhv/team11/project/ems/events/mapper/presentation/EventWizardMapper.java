package fhv.team11.project.ems.events.mapper.presentation;

import fhv.team11.project.ems.commons.mapper.IBiPresentationDomainMapper;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.events.Event;
import fhv.team11.project.ems.domain.events.EventDate;
import fhv.team11.project.ems.events.transfer.EventDateDTO;
import fhv.team11.project.ems.events.transfer.EventWizard;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class EventWizardMapper implements IBiPresentationDomainMapper<EventWizard, Event> {
    public static final EventWizardMapper INSTANCE = new EventWizardMapper();

    private EventWizardMapper() {}

    @Override
    public EventWizard getView(Event domain) {
        EventWizard eventWizard = new EventWizard();
        for (EventDate eventDate : domain.getEventDates()) {
            eventWizard.addEventDate(EventDateDTOMapper.INSTANCE.getView(eventDate));
        }
        return eventWizard;
    }

    @Override
    public Event getDomain(EventWizard presentationObject) throws DomainValidationException {
        List<EventDate> eventDates = new ArrayList<>();
        for (EventDateDTO eventDateDTO : presentationObject.getEventDates()) {
            eventDates.add(EventDateDTOMapper.INSTANCE.getDomain(eventDateDTO));
        }
        return new Event(null, eventDates);
    }
}
