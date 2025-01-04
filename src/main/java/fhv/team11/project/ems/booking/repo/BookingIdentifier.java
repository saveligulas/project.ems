package fhv.team11.project.ems.booking.repo;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class BookingIdentifier {

    //TODO: Remove auto generated and generate in domain
    @Id
    private UUID token;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private BookingEntity booking;
}
