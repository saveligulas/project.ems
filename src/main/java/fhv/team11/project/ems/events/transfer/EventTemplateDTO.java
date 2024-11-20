package fhv.team11.project.ems.events.transfer;

import fhv.team11.project.ems.commons.address.AddressDTO;
import fhv.team11.project.ems.commons.validation.model.IModelAttribute;
import fhv.team11.project.ems.events.repo.EventCategory;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EventTemplateDTO implements IModelAttribute {
    @NotBlank(message = "Please enter a name")
    private String name;
    @NotNull(message = "Please select a category")
    private EventCategory category;

    private double price;
    private int maxParticipants;
    private int minParticipants;

    @NotNull(message = "Please enter an address")
    @Valid
    private AddressDTO address;
}
