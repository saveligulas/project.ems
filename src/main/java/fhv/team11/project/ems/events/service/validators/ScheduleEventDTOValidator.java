package fhv.team11.project.ems.events.service.validators;

import fhv.team11.project.ems.commons.validation.domain.IDomainValidator;
import fhv.team11.project.ems.commons.validation.domain.ValidatorFor;
import fhv.team11.project.ems.events.transfer.EventScheduleDTO;
import fhv.team11.project.ems.events.validation.StartEndSchedule;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

import java.beans.PropertyEditor;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Component
@ValidatorFor(EventScheduleDTO.class)
public class ScheduleEventDTOValidator implements IDomainValidator<EventScheduleDTO> {

    private final Validator validator;

    @Autowired
    public ScheduleEventDTOValidator(Validator validator){
        this.validator = validator;
    }

    @AllArgsConstructor
    @Getter
    @StartEndSchedule(min= "startTime", max = "endTime")
    private static class ScheduleEventValidation{
        @NotNull(message = "Please set a start time")
        private LocalTime startTime;
        @NotNull(message = "Please set an end time")
        private LocalTime endTime;
    }
    @Override
    public BindingResult validate(EventScheduleDTO eventScheduleDTO) {
        return null;
    }
}
