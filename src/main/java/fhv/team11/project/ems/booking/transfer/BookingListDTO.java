package fhv.team11.project.ems.booking.transfer;

import fhv.team11.project.ems.booking.repo.BookingStatus;
import fhv.team11.project.ems.commons.address.AddressDTO;
import fhv.team11.project.ems.customer.transfer.CustomerProfileDTO;
import fhv.team11.project.ems.events.transfer.ActiveEventListDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BookingListDTO {

    private Long id;

    private Long financerId;

    private String eventName;

    private LocalDate startDate;

    private boolean eventHasMultipleDays;

    private int bookedPlaces;

    private BookingStatus status;
}
