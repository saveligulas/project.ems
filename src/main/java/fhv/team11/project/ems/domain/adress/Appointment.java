package fhv.team11.project.ems.domain.adress;

import fhv.team11.project.ems.domain.commons.*;
import fhv.team11.project.ems.domain.commons.exception.DomainFieldException;
import fhv.team11.project.ems.domain.commons.exception.DomainInstantiationException;
import fhv.team11.project.ems.domain.commons.exception.DomainStateException;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.events.transfer.AppointmentDTO;
import lombok.Getter;
import org.jspecify.annotations.Nullable;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Appointment implements IDomainObject, Comparable<Appointment> {
    private final DomainObjectConstructorHelper constructorHelper;

    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private String title;
    @Nullable
    private String description;

    public Appointment(Long id, LocalTime startTime, LocalTime endTime, String title, String description) throws DomainValidationException {
        constructorHelper = new DomainObjectConstructorHelper();

        setId(id);
        setStartEndTime(startTime, endTime);
        setTitle(title);
        setDescription(description);

        constructorHelper.finish();
    }

    private void setDescription(String description) throws DomainFieldException {
        if (description != null && StringValidator.isValid(description, 0, 2000)) {
            String fieldName = "description";
            String errorMessage = "Description must be between 0 and 2000 characters";
            handleError(fieldName, errorMessage, constructorHelper);
        }
        this.description = description;
    }

    private void setTitle(String title) throws DomainFieldException {
        if (!StringValidator.isValid(title, 5, 100)) {
            String fieldName = "title";
            String errorMessage = "Title must be between 5 and 100 characters";
            handleError(fieldName, errorMessage, constructorHelper);
        }
        this.title = title;
    }

    public void setId(Long id) throws DomainFieldException {
        validateId(id, constructorHelper);
        this.id = id;
    }

    public void setStartEndTime(LocalTime startTime, LocalTime endTime) throws DomainValidationException {
        String startFieldName = "startTime";
        String endFieldName = "endTime";
        String errorMessage;

        if (Validator.isNull(startTime)) {
            errorMessage = "Start time must be set";
            handleError(startFieldName, errorMessage, constructorHelper);
        }

        if (Validator.isNull(endTime)) {
            errorMessage = "End time must be set";
            handleError(endFieldName, errorMessage, constructorHelper);
        }

        if (!StartEndTimeValidator.isValid(startTime,endTime)) {
            errorMessage = "Start time must be before end time";
            if (constructorHelper.isBeingConstructed()) {
                constructorHelper.add(new DomainStateError(errorMessage));
            } else {
                throw new DomainStateException(List.of(errorMessage));
            }
        }

        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public int compareTo(Appointment o) {
        int startTimeComparison = this.startTime.compareTo(o.startTime);

        if (startTimeComparison == 0) {
            return this.endTime.compareTo(o.endTime);
        }

        return startTimeComparison;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Appointment other = (Appointment) obj;

        return this.startTime.isBefore(other.endTime) && this.endTime.isAfter(other.startTime);
    }
}
