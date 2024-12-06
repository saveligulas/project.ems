package fhv.team11.project.ems.events.error;

import org.springframework.validation.BindingResult;

public class ScheduleEventDTOValidationException extends RuntimeException{
    private final BindingResult bindingResult;

    public ScheduleEventDTOValidationException(BindingResult bindingResult) {
        super("Validation failed");
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
