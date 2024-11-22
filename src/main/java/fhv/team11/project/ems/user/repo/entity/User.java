package fhv.team11.project.ems.user.repo.entity;

import fhv.team11.project.ems.booking.repo.Booking;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {
    @Id
    private Long id;

    @OneToMany(mappedBy = "participant")
    private Set<Booking> participantBookings;

    @OneToMany(mappedBy = "reservationist")
    private Set<Booking> reservationistBookings;
}
