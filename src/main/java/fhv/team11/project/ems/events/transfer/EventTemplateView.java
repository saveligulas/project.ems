package fhv.team11.project.ems.events.transfer;

import fhv.team11.project.ems.commons.address.AddressDTO;
import fhv.team11.project.ems.events.repo.EventCategory;
import lombok.Data;

@Data
public class EventTemplateView {
    private Long id;
    private String name;
    private EventCategory category;
    private double price;
    private int maxParticipants;
    private int minParticipants;
    private AddressDTO address;
}
