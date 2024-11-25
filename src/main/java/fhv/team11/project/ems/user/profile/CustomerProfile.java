package fhv.team11.project.ems.user.profile;

import fhv.team11.project.ems.commons.address.Address;
import jakarta.persistence.*;

@Entity
public class CustomerProfile extends UserProfile{

    private String firstName;
    private String lastName;

    //TODO: ManyToMany
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    private String phoneNumber;
}
