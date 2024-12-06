package fhv.team11.project.ems.events.transfer;

import fhv.team11.project.ems.commons.validation.domain.DomainValidatorFactory;
import fhv.team11.project.ems.commons.validation.model.IModelAttribute;
import fhv.team11.project.ems.events.error.EventTemplateDTOValidationException;
import fhv.team11.project.ems.events.error.ScheduleEventDTOValidationException;
import fhv.team11.project.ems.events.validation.DateDifference;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.TreeSet;

@Data
public class ActiveEventWizardDTO implements IModelAttribute {
    private final DomainValidatorFactory domainValidatorFactory;

    public ActiveEventWizardDTO(DomainValidatorFactory domainValidatorFactory){
        this.domainValidatorFactory = domainValidatorFactory;
    }

    private TreeSet<ActiveEventDateDTO> activeEventDates = new TreeSet<>();
    private ScheduleEventDTO scheduleEvent;

    public void setScheduleEvent(ScheduleEventDTO scheduleEvent){
        BindingResult bindingResult = domainValidatorFactory.getValidator(ScheduleEventDTO.class).validate(scheduleEvent);
        bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage())); // Debugging
        if (bindingResult.hasErrors()) {
            throw new ScheduleEventDTOValidationException(bindingResult);
        }
        this.scheduleEvent = scheduleEvent;
    }
    public void addActiveEvent(ActiveEventDateDTO activeEventDateDTO) {
        BindingResult bindingResult = domainValidatorFactory.getValidator(ActiveEventDateDTO.class).validate(activeEventDateDTO);
        bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage())); // Debugging
        if (bindingResult.hasErrors()) {
            throw new ScheduleEventDTOValidationException(bindingResult);
        }
        activeEventDates.add(activeEventDateDTO);
    }

}
