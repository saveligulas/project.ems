package fhv.team11.project.ems.customer;

import fhv.team11.project.ems.commons.address.Address;
import fhv.team11.project.ems.user.profile.UserProfileEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class CustomerProfileEntityEntity extends UserProfileEntity {

    private String firstName;
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    private String phoneNumber;
    private Integer secret;
}
