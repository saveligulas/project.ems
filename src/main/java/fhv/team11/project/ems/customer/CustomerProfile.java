package fhv.team11.project.ems.customer;

import fhv.team11.project.ems.commons.address.Address;
import fhv.team11.project.ems.user.profile.UserProfile;
import jakarta.persistence.*;

@Entity
public class CustomerProfile extends UserProfile {

    private String firstName;
    private String lastName;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    private String phoneNumber;
}
