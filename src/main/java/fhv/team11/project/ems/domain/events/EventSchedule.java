package fhv.team11.project.ems.domain.events;

import fhv.team11.project.ems.domain.adress.Appointment;
import fhv.team11.project.ems.domain.commons.*;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@NullMarked
public class EventSchedule implements IDomainObject {
    private final DomainObjectConstructorHelper constructorHelper;

    @Nullable
    private Long id;
    private final List<Appointment> appointments = new ArrayList<>();

    public EventSchedule() throws DomainValidationException {
        this(null, new ArrayList<>());
    }

    public EventSchedule(@Nullable Long id, List<Appointment> appointments) throws DomainValidationException {
        this.constructorHelper = new DomainObjectConstructorHelper();

        setId(id);
        for (Appointment appointment : appointments) {
            addAppointment(appointment);
        }

        this.constructorHelper.finish();
    }

    public void setId(@Nullable Long id) throws DomainValidationException {
        if (!IdValidator.isValid(id, constructorHelper)) {
            String fieldName = "id";
            String errorMessage = "Id is invalid";

            handleError(fieldName,errorMessage,constructorHelper);
        }

        this.id = id;
    }

    public void addAppointment(Appointment appointment) throws DomainValidationException {
        if (appointments.contains(appointment)) {
            handleError("Appointment Overlaps in Schedule", constructorHelper);
        }
        this.appointments.add(appointment);
        Collections.sort(appointments);
    }
}
