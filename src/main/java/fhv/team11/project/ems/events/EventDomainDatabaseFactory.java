package fhv.team11.project.ems.events;

import fhv.team11.project.ems.commons.domain.DomainDatabaseFactory;
import fhv.team11.project.ems.commons.domain.IFindByIdDomainDatabaseMapper;
import fhv.team11.project.ems.commons.domain.IHandleDomainPersistenceShallow;
import fhv.team11.project.ems.commons.domain.ISimpleDomainDatabaseMapper;
import fhv.team11.project.ems.commons.error.EntityNotFoundException;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.events.Event;
import fhv.team11.project.ems.domain.events.EventDate;
import fhv.team11.project.ems.events.mapper.persistence.EventDateDomainMapper;
import fhv.team11.project.ems.events.repo.ActiveEvent;
import fhv.team11.project.ems.events.repo.ActiveEventRepository;
import fhv.team11.project.ems.events.repo.EventDateEntity;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventDomainDatabaseFactory extends DomainDatabaseFactory implements IHandleDomainPersistenceShallow<Event>, IFindByIdDomainDatabaseMapper<Event, Long>, ISimpleDomainDatabaseMapper<Event, ActiveEvent> {

    private final ActiveEventRepository activeEventRepository;
    private final EventTemplateDomainDatabaseFactory eventTemplateDomainDatabaseFactory;

    @Autowired
    public EventDomainDatabaseFactory(ActiveEventRepository activeEventRepository, EventTemplateDomainDatabaseFactory eventTemplateDomainDatabaseFactory) {
        this.activeEventRepository = activeEventRepository;
        this.eventTemplateDomainDatabaseFactory = eventTemplateDomainDatabaseFactory;
    }

    @Override
    public void persist(Event domainObject) throws DomainValidationException {
        ActiveEvent activeEvent = new ActiveEvent();
        activeEvent.setId(null);
        activeEvent.setEventDates(domainObject.getEventDates()
                .stream()
                .map(EventDateDomainMapper.INSTANCE::toEntity)
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
                .map(EventDateDomainMapper.INSTANCE::toEntity)
                .collect(Collectors.toSet()));
        activeEvent.setEventTemplate(eventTemplateDomainDatabaseFactory.toEntity(domain.getEventTemplate()));
        return activeEvent;
    }

    @Override
    public Event toDomain(ActiveEvent entity) throws DomainValidationException {
        List<EventDate> eventDates = new ArrayList<>();
        for (EventDateEntity eventDateEntity : entity.getEventDates()) {
            eventDates.add(EventDateDomainMapper.INSTANCE.toDomain(eventDateEntity));
        }
        return new Event(
                entity.getId(),
                eventDates,
                eventTemplateDomainDatabaseFactory.toDomain(entity.getEventTemplate())
        );
    }
}
