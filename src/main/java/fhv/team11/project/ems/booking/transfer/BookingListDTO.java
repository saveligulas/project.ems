package fhv.team11.project.ems.booking.transfer;

import fhv.team11.project.ems.booking.repo.Deposit;
import fhv.team11.project.ems.commons.address.AddressDTO;
import fhv.team11.project.ems.customer.transfer.CustomerProfileDTO;
import fhv.team11.project.ems.events.transfer.ActiveEventListDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BookingListDTO {

    private Long id;

    private AddressDTO participantAddress;
    //TODO participant to String
    private CustomerProfileDTO financer;

    private ActiveEventListDTO bookedEvent;

    private Deposit deposit;

    private int bookedPlaces;

    private LocalDate cancellationDeadline;

    private LocalDate optionDate;

    private BigDecimal price;

    private BookingIdentifierListDTO identifier;
}
