package fhv.team11.project.ems.events.transfer;

import fhv.team11.project.ems.commons.address.AddressDTO;
import fhv.team11.project.ems.commons.validation.model.IModelAttribute;
import fhv.team11.project.ems.events.repo.EventCategory;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EventTemplateDTO implements IModelAttribute {
    private String name;
    private EventCategory category;
    private double price;
    private int maxParticipants;
    private int minParticipants;
    private AddressDTO address;
}
