package fhv.team11.project.ems.events.repo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @OneToOne(mappedBy = "schedule", cascade = CascadeType.ALL)
    private Appointment appointment;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="active_event_id", nullable=false)
    private ActiveEvent activeEvent;
}
