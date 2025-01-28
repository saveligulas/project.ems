package fhv.team11.project.ems.events.repo;

import fhv.team11.project.ems.booking.repo.BookingIdentifier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String  name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="schedule_id")
    private Schedule schedule;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "event_date_booking",
            joinColumns = @JoinColumn(name = "event_date_id"),
            inverseJoinColumns = @JoinColumn(name = "booking_identifier_token")
    )
    private List<BookingIdentifier> checkedInBookingIdentifiers;
}
