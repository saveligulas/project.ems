package fhv.team11.project.ems.booking.transfer;

import fhv.team11.project.ems.booking.repo.Deposite;
import fhv.team11.project.ems.commons.address.Address;
import fhv.team11.project.ems.commons.address.AddressDTO;
import fhv.team11.project.ems.commons.validation.model.IModelAttribute;
import fhv.team11.project.ems.events.transfer.ActiveEventListDTO;
import fhv.team11.project.ems.events.transfer.ActiveEventWizardDTO;
import fhv.team11.project.ems.user.transfer.UserDTO;
import jakarta.validation.Valid;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BookingDTO implements IModelAttribute {

    private Long id;

    @Valid
    private AddressDTO participantAddress;

    @Valid
    private UserDTO participant;
    //TODO participant to String
    @Valid
    private UserDTO reservationist;

    @Valid
    private ActiveEventListDTO bookedEvent;

    private Deposite deposite;

    private int bookedPlaces;

    private LocalDate cancellationDeadline;

    private LocalDate optionDate;

    private BigDecimal price;


}
