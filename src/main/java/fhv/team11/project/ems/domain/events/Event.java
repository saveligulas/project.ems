package fhv.team11.project.ems.domain.events;


import fhv.team11.project.ems.domain.adress.Address;
import fhv.team11.project.ems.domain.booking.Booking;
import fhv.team11.project.ems.domain.commons.exception.DomainStateException;
import fhv.team11.project.ems.domain.commons.exception.error.DomainObjectConstructorHelper;
import fhv.team11.project.ems.domain.commons.interfaces.IDomainObject;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.commons.interfaces.ILineItem;
import fhv.team11.project.ems.domain.commons.interfaces.IRepresentRealWorldEntity;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Getter
@NullMarked
public class Event implements IDomainObject, IRepresentRealWorldEntity {

    private final DomainObjectConstructorHelper constructorHelper;

    //TODO: add name field in all layers
    @Nullable
    private Long id;
    @Nullable
    private EventTemplate eventTemplate;
    @Setter
    private List<EventDate> eventDates;
    @Setter
    private int bookedPlaces = 0;

    public Event(@Nullable Long id, List<EventDate> eventDates) throws DomainValidationException {
        this(id, eventDates, null);
    }

    public Event(@Nullable Long id, List<EventDate> eventDates, @Nullable EventTemplate eventTemplate) throws DomainValidationException {
        this.constructorHelper = new DomainObjectConstructorHelper();

        setId(id);
        Collections.sort(eventDates);
        setEventDates(eventDates);
        this.eventTemplate = eventTemplate;

        this.constructorHelper.finish();
    }

    private Event(@Nullable Long id, List<EventDate> eventDates, @Nullable EventTemplate eventTemplate, boolean bypass) {
        this.constructorHelper = new DomainObjectConstructorHelper();
        this.id = id;
        this.eventDates = eventDates;
        this.eventTemplate = eventTemplate;
    }


    public static Event createWithoutValidation(@Nullable Long id, List<EventDate> eventDates) {
        return createWithoutValidation(id, eventDates, null);
    }

    public static Event createWithoutValidation(@Nullable Long id, List<EventDate> eventDates, @Nullable EventTemplate eventTemplate) {
        return new Event(id, eventDates, eventTemplate, true);
    }

    public void setId(@Nullable Long id) throws DomainValidationException {
        validateId(id, constructorHelper);
        this.id = id;
    }

    public void setEventTemplate(EventTemplate eventTemplate) {
        this.eventTemplate = eventTemplate;
    }

    public void addEventDate(EventDate eventDate) throws DomainValidationException {
        if (eventDates.contains(eventDate)) {
            handleError("Duplicate event date", constructorHelper);
        }
        eventDates.add(eventDate);
        Collections.sort(eventDates);
    }

    public @Nullable EventDate getEventDateForDate(LocalDate localDate) {
        for (EventDate eventDate : eventDates) {
            if (eventDate.getDate().equals(localDate)) {
                return eventDate;
            }
        }
        return null;
    }

    public LocalDate getFirstEventDate() throws DomainStateException {
        if (isDatesEmpty()) {
            throw new DomainStateException(List.of("No event dates to access"));
        }

        return eventDates.get(0).getDate();
    }

    public LocalDate getLastEventDate() throws DomainStateException {
        if (isDatesEmpty()) {
            throw new DomainStateException(List.of("No event dates to access"));
        }

        return eventDates.get(eventDates.size() - 1).getDate();
    }

    public boolean isDatesEmpty() {
        return eventDates.isEmpty();
    }

    public int getPlacesLeft() {
        if (eventTemplate == null) {
            return -1;
        }
        return eventTemplate.getMaxParticipants() - bookedPlaces;
    }

    //TODO: exception if no slots are available and if not increase bookedPlaces
    public void bookPlace() {

    }

    @Override
    public Address getAddress() {
        return eventTemplate.getAddress();
    }

    //TODO: change this once organizations are implemented
    @Override
    public String getSurname() {
        return "Eventastic Inc.";
    }

    @Override
    public String getName() {
        return "";
    }
}
