package fhv.team11.project.ems.domain.events;

import fhv.team11.project.ems.domain.commons.*;
import fhv.team11.project.ems.domain.commons.exception.DomainFieldValidationException;
import fhv.team11.project.ems.domain.commons.exception.DomainInstantiationException;
import lombok.Getter;

import javax.annotation.Nullable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

@Getter
public class ScheduleEventModel implements IDomainObject {
    private final DomainObjectConstructorHelper constructorHelper;

    @Nullable
    private Long id;

    private LocalTime startTime;
    private LocalTime endTime;

    public ScheduleEventModel(Long id, LocalTime startTime, LocalTime endTime) throws DomainFieldValidationException {
        this.constructorHelper = new DomainObjectConstructorHelper();

        setId(id);
        setStartEndTime(startTime,endTime);

        this.constructorHelper.finish();
    }

    public void setId(Long id) throws DomainFieldValidationException {

        if (!IndexValidator.isValid(id)) {
            String fieldName = "Id";
            String errorMessage = "Id must be positive";

            handleError(fieldName,errorMessage,constructorHelper);
        }

        this.id = id;
    }

    public void setStartEndTime(LocalTime startTime, LocalTime endTime) throws DomainFieldValidationException {

        if (!TimeValidator.isValid(endTime)) {
            String fieldName = "endTime";
            String errorMessage = "end time must be set";

            handleError(fieldName,errorMessage,constructorHelper);
        }

        if (!TimeValidator.isValid(startTime)) {
            String fieldName = "startTime";
            String errorMessage = "start time must be set";

            handleError(fieldName,errorMessage,constructorHelper);
        }

        if (!StartEndTimeValidator.isValid(startTime,endTime)) {
            ArrayList<String> fieldNames = new ArrayList<>();
            fieldNames.add("startTime");
            fieldNames.add("endTime");
            String errorMessage = "Start time must be before end time";

            handleError(fieldNames.get(0),errorMessage,constructorHelper);
            handleError(fieldNames.get(1),errorMessage,constructorHelper);
        }



        this.startTime = startTime;
        this.endTime = endTime;
    }


}
