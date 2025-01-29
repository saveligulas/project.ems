package fhv.team11.project.ems.booking.repo;

import fhv.team11.project.ems.commons.address.AddressEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class RealWorldEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String surname;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", unique = true)
    private AddressEntity address;
}
