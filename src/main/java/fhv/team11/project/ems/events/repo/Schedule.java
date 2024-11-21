package fhv.team11.project.ems.events.repo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;


@Entity
@Getter
@Setter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private Set<EventDate> eventDates;

    @OneToMany(mappedBy = "schedule")
    private Set<Appointment> appointments;
}
