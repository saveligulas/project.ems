package fhv.team11.project.ems.booking.repo;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class BookingIdentifier {

    @Id
    @GeneratedValue
    private UUID token;

    private BookingStatus status;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
