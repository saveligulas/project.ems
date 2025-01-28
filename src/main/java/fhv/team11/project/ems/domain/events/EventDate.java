package fhv.team11.project.ems.domain.events;


import fhv.team11.project.ems.domain.commons.*;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.commons.exception.error.DomainObjectConstructorHelper;
import fhv.team11.project.ems.domain.commons.interfaces.IDomainObject;
import fhv.team11.project.ems.domain.commons.validation.DateTimeValidator;
import fhv.team11.project.ems.domain.commons.validation.StringValidator;
import lombok.Getter;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
public class EventDate implements IDomainObject, Comparable<EventDate> {
    private final DomainObjectConstructorHelper constructorHelper;

    @Nullable
    private Long id;
    private LocalDate date;
    @Nullable
    private String name;
    private EventSchedule schedule;
    private List<UUID> checkedInBookingIdentifiers;

    public EventDate(Long id, LocalDate date) throws DomainValidationException {
        this(id, date, date.toString());
    }

    public EventDate(Long id, LocalDate date, String name) throws DomainValidationException {
        this(id, date, name, new EventSchedule(), new ArrayList<>());
    }

    public EventDate(Long id, LocalDate date, String name, EventSchedule eventSchedule, List<UUID> checkedInBookingIdentifiers) throws DomainValidationException {
        this.constructorHelper = new DomainObjectConstructorHelper();

        setId(id);
        setDate(date);
        if (Validator.isBlank(name)) {
            setName(date.toString());
        } else {
            setName(name);
        }
        setSchedule(eventSchedule);
        this.checkedInBookingIdentifiers = checkedInBookingIdentifiers;

        this.constructorHelper.finish();
    }

    public void setId(Long id) throws DomainValidationException {
        validateId(id, constructorHelper);
        this.id = id;
    }

    private void setName(String name) throws DomainValidationException {
        String fieldName = "name";
        String errorMessage;

        if (Validator.isNotNull(name) && !Validator.isBlank(name) && !StringValidator.isValid(name, 5)) {
            errorMessage = "Name is too short";
            handleError(fieldName, errorMessage, constructorHelper);
        }

        this.name = name;
    }

    //TODO: Bug when retrieving older event from database
    //TODO: Maybe convert event after it happened so this cannot happen (new class needed for historic event archive)
    public void setDate(LocalDate date) throws DomainValidationException {
        String fieldName = "date";
        String errorMessage;

        if (Validator.isNull(date)) {
            errorMessage = "Date is required";
            handleError(fieldName, errorMessage, constructorHelper);
        }

        if (DateTimeValidator.isPastDate(date)) {
            errorMessage = "Date must be in the future";
            handleError(fieldName, errorMessage, constructorHelper);
        }

        if (DateTimeValidator.isWithinRange(date, LocalDate.now().plusYears(2), LocalDate.MAX)) {
            errorMessage = "Date must not be more than 2 years in the future";
            handleError(fieldName, errorMessage, constructorHelper);
        }

        this.date = date;
    }

    public void setSchedule(EventSchedule schedule) throws DomainValidationException {
        this.schedule = schedule;
    }

    public void addBookingIdentifier(UUID bookingIdentifier) {
        this.checkedInBookingIdentifiers.add(bookingIdentifier);
    }

    public boolean isCheckedInForDate(UUID bookingIdentifier) {
        return this.checkedInBookingIdentifiers.contains(bookingIdentifier);
    }

    @Override
    public int compareTo(EventDate o) {
        return this.date.compareTo(o.date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventDate that = (EventDate) o;
        return Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(date);
    }
}
