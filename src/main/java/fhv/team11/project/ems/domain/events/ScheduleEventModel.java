package fhv.team11.project.ems.domain.events;

import fhv.team11.project.ems.commons.domain.DomainFieldCollectionValidationException;
import fhv.team11.project.ems.commons.domain.DomainFieldValidationException;
import fhv.team11.project.ems.commons.domain.DomainInstantiationException;
import fhv.team11.project.ems.commons.domain.IDomainObject;
import fhv.team11.project.ems.domain.commons.TimeValidator;
import fhv.team11.project.ems.domain.commons.IndexValidator;
import fhv.team11.project.ems.domain.commons.StartEndTimeValidator;

import javax.annotation.Nullable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class ScheduleEventModel implements IDomainObject {
    @Nullable
    private transient HashMap<String, String> fieldErrors;

    @Nullable
    private Long id;

    private LocalTime startTime;
    private LocalTime endTime;

    public ScheduleEventModel(Long id, LocalTime startTime, LocalTime endTime) throws DomainFieldValidationException, DomainFieldCollectionValidationException {
        fieldErrors = new HashMap<>();

        setId(id);
        setStartEndTime(startTime,endTime);
    }

    public void setId(Long id) throws DomainFieldValidationException {

        if (!IndexValidator.isValid(id)) {
            String fieldName = "Id";
            String errorMessage = "Id must be positive";

            if (this.isInstantiated()) {
                throw new DomainFieldValidationException(fieldName, errorMessage);
            } else {
                fieldErrors.put(fieldName, errorMessage);
            }
        }

        this.id = id;
    }

    public void setStartEndTime(LocalTime startTime, LocalTime endTime) throws DomainFieldValidationException, DomainFieldCollectionValidationException {

        if (!TimeValidator.isValid(endTime)) {
            String fieldName = "endTime";
            String errorMessage = "end time must be set";

            if (this.isInstantiated()) {
                throw new DomainFieldValidationException(fieldName, errorMessage);
            } else {
                fieldErrors.put(fieldName, errorMessage);
            }
        }

        if (!TimeValidator.isValid(startTime)) {
            String fieldName = "startTime";
            String errorMessage = "start time must be set";

            if (this.isInstantiated()) {
                throw new DomainFieldValidationException(fieldName, errorMessage);
            } else {
                fieldErrors.put(fieldName, errorMessage);
            }
        }

        if (!StartEndTimeValidator.isValid(startTime,endTime)) {
            ArrayList<String> fieldNames = new ArrayList<>();
            fieldNames.add("startTime");
            fieldNames.add("endTime");
            String errorMessage = "Start time must be before end time";

            if (this.isInstantiated()) {
                throw new DomainFieldCollectionValidationException(fieldNames, errorMessage);
            } else {
                fieldErrors.put(fieldNames.get(0), errorMessage);
                fieldErrors.put(fieldNames.get(1), errorMessage);
            }
        }



        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public void checkFieldErrors() throws DomainInstantiationException {
        if (fieldErrors != null && !fieldErrors.isEmpty()) {
            throw new DomainInstantiationException(fieldErrors);
        }
        fieldErrors = null;
    }

    @Override
    public boolean isInstantiated() {
        return fieldErrors == null;
    }
}
