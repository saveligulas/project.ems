package fhv.team11.project.ems.booking.transfer;

import fhv.team11.project.ems.booking.repo.Deposite;
import fhv.team11.project.ems.commons.address.Address;
import fhv.team11.project.ems.commons.address.AddressDTO;
import fhv.team11.project.ems.events.transfer.ActiveEventListDTO;
import fhv.team11.project.ems.events.transfer.ActiveEventWizardDTO;
import fhv.team11.project.ems.user.transfer.UserDTO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingDTO {

    private Long id;

    private AddressDTO participantAddress;

    private UserDTO participant;
    private UserDTO reservationist;

    private ActiveEventListDTO bookedEvent;

    private Deposite deposite;

    private int bookedPlaces;

    private LocalDate cancellationDeadline;

    private LocalDate optionDate;

    private int price;

}
