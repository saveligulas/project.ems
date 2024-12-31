package fhv.team11.project.ems.events.repo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Getter
@Setter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TODO: OneToOne relationship
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private Set<EventDateEntity> eventDates;

    @OneToMany(mappedBy = "schedule")
    private Set<Appointment> appointments;
}
