package fhv.team11.project.ems.booking.repo;

import fhv.team11.project.ems.commons.address.Address;
import fhv.team11.project.ems.events.repo.ActiveEvent;
import fhv.team11.project.ems.user.entity.UserEntity;
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
    @JoinColumn(name = "address_id")
    private Address participantAddress;
    
    private String participant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "financer_id",nullable = true)
    private UserEntity financer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "active_event_id")
    private ActiveEvent bookedEvent;

    @Enumerated(EnumType.ORDINAL)
    private Deposite deposite;

    private int bookedPlaces;

    private LocalDate cancellationDeadline;

    private LocalDate optionDate;

    private BigDecimal price;

    @OneToOne(mappedBy = "booking", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private BookingIdentifier bookingIdentifier;

}
