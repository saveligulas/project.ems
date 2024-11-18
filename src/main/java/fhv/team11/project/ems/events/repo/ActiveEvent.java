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

    private Boolean activeFlag;

    @OneToMany(mappedBy = "activeEvent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Schedule> schedules;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id",nullable = false)
    private EventTemplate eventTemplate;



}
