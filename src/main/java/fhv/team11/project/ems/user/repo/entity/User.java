package fhv.team11.project.ems.user.repo.entity;

import fhv.team11.project.ems.booking.repo.Booking;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    private Long id;

    @OneToMany(mappedBy = "participant", fetch = FetchType.LAZY)
    private Set<Booking> participantBookings;

    @OneToMany(mappedBy = "reservationist", fetch = FetchType.LAZY)
    private Set<Booking> reservationistBookings;

    public User(Long id) {
        this.id = id;
    }
}
