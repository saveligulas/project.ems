package fhv.team11.project.ems.booking.repo;


import fhv.team11.project.ems.events.repo.EventDateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingIdentifier {

    //TODO: Remove auto generated and generate in domain
    @Id
    private UUID token;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private BookingEntity booking;

    @ManyToMany(mappedBy = "checkedInBookingIdentifiers")
    private List<EventDateEntity> eventDates;
}
