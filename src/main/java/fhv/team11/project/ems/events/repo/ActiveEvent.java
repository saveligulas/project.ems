package fhv.team11.project.ems.events.repo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
public class ActiveEvent {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="active_event_schedule",
            joinColumns = @JoinColumn(name = "active_event_id"),
            inverseJoinColumns = @JoinColumn(name="schedule_id")
    )
    private Set<Schedule> schedules;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id",nullable = false)
    private EventTemplate eventTemplate;



}
