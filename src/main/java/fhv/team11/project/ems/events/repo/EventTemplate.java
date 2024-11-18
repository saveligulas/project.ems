package fhv.team11.project.ems.events.repo;

import fhv.team11.project.ems.commons.address.Address;
import fhv.team11.project.ems.user.repo.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Entity
@Getter
@Setter
public class EventTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private EventCategory category;
    private BigDecimal price;
    private int minParticipants;
    private int maxParticipants;
    private int overbookingPlaces;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;


    @OneToMany(mappedBy = "eventTemplate", fetch = FetchType.EAGER)
    private Set<ActiveEvent> activeEvents;
}
