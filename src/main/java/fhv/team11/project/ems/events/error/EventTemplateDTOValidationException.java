package fhv.team11.project.ems.events.error;

import fhv.team11.project.ems.commons.validation.domain.error.BindingResultException;
import org.springframework.validation.BindingResult;

public class EventTemplateDTOValidationException extends RuntimeException {
    private final BindingResult bindingResult;

    public EventTemplateDTOValidationException(BindingResult bindingResult) {
        super("Validation failed");
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}

