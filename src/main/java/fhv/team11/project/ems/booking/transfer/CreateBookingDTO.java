package fhv.team11.project.ems.booking.transfer;

import fhv.team11.project.ems.booking.repo.Deposit;
import fhv.team11.project.ems.commons.address.AddressDTO;
import fhv.team11.project.ems.commons.validation.model.IModelAttribute;
import fhv.team11.project.ems.customer.transfer.CustomerProfileDTO;
import fhv.team11.project.ems.events.transfer.ActiveEventListDTO;
import lombok.Data;

@Data
public class CreateBookingDTO implements IModelAttribute {
    private Long financerId;
    private Integer bookedPlaces;
}
