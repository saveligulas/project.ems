package fhv.team11.project.ems.customer;

import fhv.team11.project.ems.commons.address.AddressEntity;
import fhv.team11.project.ems.user.profile.UserProfileEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class CustomerProfileEntity extends UserProfileEntity {

    private String firstName;
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressEntity addressEntity;

    private String phoneNumber;
    private Integer secret;
}
