package fhv.team11.project.ems.booking.repo;

import fhv.team11.project.ems.commons.address.Address;
import fhv.team11.project.ems.customer.CustomerProfile;
import fhv.team11.project.ems.events.repo.ActiveEvent;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "financer_id",nullable = true)
    private CustomerProfile financer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "active_event_id")
    private ActiveEvent bookedEvent;

    @Enumerated(EnumType.ORDINAL)
    private Deposit deposit;

    private Integer bookedPlaces;

    //TODO: set them from Service, by predefined dates by event organizer
    private LocalDate cancellationDeadline;

    private LocalDate optionDate;

    //TODO: Load in service
    private BigDecimal price;

    @OneToOne
    private BookingIdentifier bookingIdentifier;
}
