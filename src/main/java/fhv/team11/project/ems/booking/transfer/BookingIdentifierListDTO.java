package fhv.team11.project.ems.booking.transfer;

import fhv.team11.project.ems.booking.repo.BookingStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class BookingIdentifierListDTO {

    private UUID token;
    private BookingStatus status;
    private String QRCode;
}
