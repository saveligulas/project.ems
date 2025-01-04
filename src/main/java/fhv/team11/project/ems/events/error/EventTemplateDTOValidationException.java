package fhv.team11.project.ems.events.error;

import fhv.team11.project.ems.commons.validation.domain.error.BindingResultException;
import org.springframework.validation.BindingResult;

public class EventTemplateDTOValidationException extends BindingResultException {
    public EventTemplateDTOValidationException(BindingResult bindingResult) {
        super(bindingResult, "eventTemplate", "event/manage");
    }
}

