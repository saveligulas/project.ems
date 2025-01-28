package fhv.team11.project.ems.events.repo;

import fhv.team11.project.ems.booking.repo.BookingEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class ActiveEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="active_event_id")
    private Set<EventDateEntity> eventDates;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id",nullable = false)
    private EventTemplateEntity eventTemplate;

    @OneToMany(mappedBy = "bookedEvent", fetch = FetchType.LAZY)
    private Set<BookingEntity> bookings = new HashSet<>();
}
