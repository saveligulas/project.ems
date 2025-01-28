package fhv.team11.project.ems.events;

import fhv.team11.project.ems.booking.repo.BookingEntity;
import fhv.team11.project.ems.commons.domain.DomainDatabaseFactory;
import fhv.team11.project.ems.commons.domain.IFindByIdDomainDatabaseMapper;
import fhv.team11.project.ems.commons.domain.IHandleDomainPersistenceShallow;
import fhv.team11.project.ems.commons.domain.ISimpleDomainDatabaseMapper;
import fhv.team11.project.ems.commons.error.EntityNotFoundException;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.events.Event;
import fhv.team11.project.ems.domain.events.EventDate;
import fhv.team11.project.ems.events.mapper.persistence.EventDateDomainDatabaseFactory;
import fhv.team11.project.ems.events.repo.ActiveEvent;
import fhv.team11.project.ems.events.repo.ActiveEventRepository;
import fhv.team11.project.ems.events.repo.EventDateEntity;
import jakarta.persistence.PersistenceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class EventDomainDatabaseFactory extends DomainDatabaseFactory implements IHandleDomainPersistenceShallow<Event>, IFindByIdDomainDatabaseMapper<Event, Long>, ISimpleDomainDatabaseMapper<Event, ActiveEvent> {

    private final ActiveEventRepository activeEventRepository;
    private final EventTemplateDomainDatabaseFactory eventTemplateDomainDatabaseFactory;
    private final EventDateDomainDatabaseFactory eventDateDomainDatabaseFactory;

    @Autowired
    public EventDomainDatabaseFactory(ActiveEventRepository activeEventRepository, EventTemplateDomainDatabaseFactory eventTemplateDomainDatabaseFactory, EventDateDomainDatabaseFactory eventDateDomainDatabaseFactory) {
        this.activeEventRepository = activeEventRepository;
        this.eventTemplateDomainDatabaseFactory = eventTemplateDomainDatabaseFactory;

        this.eventDateDomainDatabaseFactory = eventDateDomainDatabaseFactory;
    }

    @Override
    public void persist(Event domainObject) throws DomainValidationException {
        ActiveEvent activeEvent = new ActiveEvent();
        activeEvent.setId(domainObject.getId());
        activeEvent.setEventDates(domainObject.getEventDates()
                .stream()
                .map(eventDateDomainDatabaseFactory::toEntity)
                .collect(Collectors.toSet()));
        if (domainObject.getEventTemplate() == null) {
            throw new PersistenceException("No event template set for the event");
        }
        activeEvent.setEventTemplate(eventTemplateDomainDatabaseFactory.toEntity(domainObject.getEventTemplate()));
        activeEventRepository.save(activeEvent);
    }

    @Override
    public Event getDomainById(Long id) throws DomainValidationException {
        return toDomain(activeEventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ActiveEvent.class, id)));
    }

    @Override
    public ActiveEvent toEntity(Event domain) {
        ActiveEvent activeEvent = new ActiveEvent();
        activeEvent.setId(domain.getId());
        activeEvent.setEventDates(domain.getEventDates()
                .stream()
                .map(eventDateDomainDatabaseFactory::toEntity)
                .collect(Collectors.toSet()));
        activeEvent.setEventTemplate(eventTemplateDomainDatabaseFactory.toEntity(domain.getEventTemplate()));
        return activeEvent;
    }

    @Override
    public Event toDomain(ActiveEvent entity) throws DomainValidationException {
        List<EventDate> eventDates = new ArrayList<>();
        for (EventDateEntity eventDateEntity : entity.getEventDates()) {
            eventDates.add(eventDateDomainDatabaseFactory.toDomain(eventDateEntity));
        }
        Event event = new Event(
                entity.getId(),
                eventDates,
                eventTemplateDomainDatabaseFactory.toDomain(entity.getEventTemplate())
        );
        int bookedPlaces = 0;
        for (BookingEntity booking : entity.getBookings()) {
            bookedPlaces += booking.getBookedPlaces();
        }
        event.setBookedPlaces(bookedPlaces);
        return event;
    }

    public List<Event> findAllByTemplateId(Long templateId) {
        List<ActiveEvent> activeEvents = activeEventRepository.findAllByTemplateIdWithDates(templateId);
        List<Event> events = new ArrayList<>();
        for (ActiveEvent activeEvent : activeEvents) {
            try {
                events.add(toDomain(activeEvent));
            } catch (DomainValidationException e) {
                log.error("Domain validation exception from database occurred: " + e.getFieldErrors() + " | " + e.getErrorMessages());
            }
        }
        return events;
    }
}
