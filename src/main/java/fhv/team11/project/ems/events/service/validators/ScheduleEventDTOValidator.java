package fhv.team11.project.ems.events.service.validators;

import fhv.team11.project.ems.commons.validation.domain.IDomainValidator;
import fhv.team11.project.ems.commons.validation.domain.ValidatorFor;
import fhv.team11.project.ems.events.transfer.ScheduleEventDTO;
import fhv.team11.project.ems.events.validation.StartEndSchedule;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.time.LocalTime;

@Component
@ValidatorFor(ScheduleEventDTO.class)
public class ScheduleEventDTOValidator implements IDomainValidator<ScheduleEventDTO> {

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
    public BindingResult validate(ScheduleEventDTO scheduleEventDTO) {
        BindingResult bindingResult = buildBindingResult(scheduleEventDTO);
        validator.validate(new ScheduleEventValidation(
                scheduleEventDTO.getStartTime(),
                scheduleEventDTO.getEndTime()
        ), bindingResult);
        return bindingResult;
    }

}
