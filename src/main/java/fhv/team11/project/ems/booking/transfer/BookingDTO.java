package fhv.team11.project.ems.booking.transfer;

import fhv.team11.project.ems.booking.repo.Deposit;
import fhv.team11.project.ems.commons.address.AddressDTO;
import fhv.team11.project.ems.customer.transfer.CustomerProfileDTO;
import fhv.team11.project.ems.events.transfer.ActiveEventListDTO;
import fhv.team11.project.ems.user.transfer.UserDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BookingDTO {

    private Long id;

    private AddressDTO participantAddress;

    private UserDTO participant;
    //TODO participant to String
    private CustomerProfileDTO reservationist;

    private ActiveEventListDTO bookedEvent;

    private Deposit deposite;

    private int bookedPlaces;

    private LocalDate cancellationDeadline;

    private LocalDate optionDate;

    private BigDecimal price;


}
