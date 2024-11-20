package fhv.team11.project.ems.events.transfer;

import fhv.team11.project.ems.commons.address.AddressDTO;
import fhv.team11.project.ems.events.repo.EventCategory;
import fhv.team11.project.ems.events.validation.MaxMinParticipants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@MaxMinParticipants(min = "minParticipants", max = "maxParticipants", message = "Die minimale Teilnehmeranzahl muss kleiner a ls die maximale sein")
public class EventTemplateDTO {
    @NotBlank(message = "Bitte geben sie einen Namen ein")
    private String name;
    @NotNull(message = "Bitte wählen Sie eine Kategorie aus")
    private EventCategory category;

    @PositiveOrZero(message = "Der Preis darf nicht nur Null oder Positiv sein")
    private double price;

    @Max(value = 1000L, message = "Die Anzahl der Teilnehmer darf nicht 1000 überschrieten")
    @Min(value = 0L, message = "Die Anzahl der Teilnehmer darf nicht negativ sein")
    private int maxParticipants;

    @Max(value = 1000L, message = "Die Anzahl der Teilnehmer darf nicht 1000 überschreiten")
    @Min(value = 0L, message = "Die Anzahl der Teilnehmer darf nicht negativ sein")
    private int minParticipants;

    @NotNull(message = "Die Adresse darf nicht leer sein")
    @Valid
    private AddressDTO address;
}
