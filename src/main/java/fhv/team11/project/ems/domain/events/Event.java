package fhv.team11.project.ems.domain.events;


import fhv.team11.project.ems.domain.CollectionConverter;
import fhv.team11.project.ems.domain.commons.DomainObjectConstructorHelper;
import fhv.team11.project.ems.domain.commons.IDomainObject;
import fhv.team11.project.ems.domain.commons.exception.DomainFieldException;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;


@Getter
public class Event implements IDomainObject {

    private final DomainObjectConstructorHelper constructorHelper;

    @Nullable
    private Long id;

    @Setter
    private List<EventDate> eventDates;

    public Event(Long id, List<EventDate> eventDates) throws DomainValidationException {
        this.constructorHelper = new DomainObjectConstructorHelper();

        setId(id);
        Collections.sort(eventDates);
        setEventDates(eventDates);

        this.constructorHelper.finish();
    }

    public void setId(Long id) throws DomainValidationException {
        validateId(id, constructorHelper);
        this.id = id;
    }

    public void addEventDate(EventDate eventDate) throws DomainValidationException {
        if (eventDates.contains(eventDate)) {
            handleError("Duplicate event date", constructorHelper);
        }
        eventDates.add(eventDate);
        Collections.sort(eventDates);
    }
}
