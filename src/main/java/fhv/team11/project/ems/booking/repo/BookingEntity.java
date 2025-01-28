package fhv.team11.project.ems.booking.repo;

import fhv.team11.project.ems.customer.CustomerProfileEntity;
import fhv.team11.project.ems.events.repo.ActiveEvent;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name="booking")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "financer_id",nullable = true)
    private CustomerProfileEntity financer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "active_event_id")
    private ActiveEvent bookedEvent;

    private Integer bookedPlaces;

    //TODO: Load in service
    private BigDecimal price;

    @Enumerated(EnumType.ORDINAL)
    private BookingStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deposit_id", nullable = true)
    private InvoiceEntity deposit;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id", nullable = true)
    private InvoiceEntity booking;

    @OneToOne(mappedBy = "booking", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private BookingIdentifier bookingIdentifier;
}
