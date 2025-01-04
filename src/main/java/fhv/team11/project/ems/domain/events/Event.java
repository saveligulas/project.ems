package fhv.team11.project.ems.domain.events;


import fhv.team11.project.ems.domain.commons.exception.error.DomainObjectConstructorHelper;
import fhv.team11.project.ems.domain.commons.interfaces.IDomainObject;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.List;


@Getter
@NullMarked
public class Event implements IDomainObject {

    private final DomainObjectConstructorHelper constructorHelper;

    //TODO: add name field in all layers
    @Nullable
    private Long id;
    @Nullable
    private EventTemplate eventTemplate;
    @Setter
    private List<EventDate> eventDates;

    public Event(@Nullable Long id, List<EventDate> eventDates) throws DomainValidationException {
        this(id, eventDates, null);
    }

    public Event(@Nullable Long id, List<EventDate> eventDates, @Nullable EventTemplate eventTemplate) throws DomainValidationException {
        this.constructorHelper = new DomainObjectConstructorHelper();

        setId(id);
        Collections.sort(eventDates);
        setEventDates(eventDates);
        setEventTemplate(eventTemplate);

        this.constructorHelper.finish();
    }

    public void setId(@Nullable Long id) throws DomainValidationException {
        validateId(id, constructorHelper);
        this.id = id;
    }

    public void setEventTemplate(@Nullable EventTemplate eventTemplate) {
        this.eventTemplate = eventTemplate;
    }

    public void addEventDate(EventDate eventDate) throws DomainValidationException {
        if (eventDates.contains(eventDate)) {
            handleError("Duplicate event date", constructorHelper);
        }
        eventDates.add(eventDate);
        Collections.sort(eventDates);
    }
}
