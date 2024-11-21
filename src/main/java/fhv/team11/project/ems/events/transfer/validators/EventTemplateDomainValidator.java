package fhv.team11.project.ems.events.transfer.validators;

import fhv.team11.project.ems.commons.validation.domain.IDomainValidator;
import fhv.team11.project.ems.commons.validation.domain.ValidatorFor;
import fhv.team11.project.ems.events.repo.EventTemplate;
import fhv.team11.project.ems.events.transfer.EventTemplateDTO;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

@Component
@ValidatorFor(EventTemplateDTO.class)
public class EventTemplateDomainValidator implements IDomainValidator<EventTemplateDTO> {

    private final Validator validator;

    @Autowired
    public EventTemplateDomainValidator(Validator validator) {
        this.validator = validator;
    }

    @AllArgsConstructor
    private static class EventTemplateValidation {
        @DecimalMax(value = "1000.0", message = "Der Preis darf nicht höher als 1000 sein")
        @DecimalMin(value = "0.0", message = "Der Preis darf nicht negativ sein")
        private double price;

        @Max(value = 1000L, message = "Die Anzahl der Teilnehmer darf nicht 1000 überschrieten")
        @Min(value = 0L, message = "Die Anzahl der Teilnehmer darf nicht negativ sein")
        private int maxParticipants;

        @Max(value = 1000L, message = "Die Anzahl der Teilnehmer darf nicht 1000 überschreiten")
        @Min(value = 0L, message = "Die Anzahl der Teilnehmer darf nicht negativ sein")
        private int minParticipants;
    }

    @Override
    public BindingResult validate(EventTemplateDTO eventTemplateDTO) {
        BindingResult bindingResult = buildBindingResult(eventTemplateDTO);
        validator.validate(new EventTemplateValidation(
                eventTemplateDTO.getPrice(),
                eventTemplateDTO.getMaxParticipants(),
                eventTemplateDTO.getMinParticipants()),
                bindingResult);
        return bindingResult;
    }
}
