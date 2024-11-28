package fhv.team11.project.ems.user.entity;

import fhv.team11.project.ems.admin.AdministratorProfile;
import fhv.team11.project.ems.customer.CustomerProfile;
import fhv.team11.project.ems.user.profile.BackOfficeProfile;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class UserEntityDetails {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_profile_id")
    private CustomerProfile customerProfile;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "back_office_profile_id")
    private BackOfficeProfile backOfficeProfile;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "administrator_profile_id")
    private AdministratorProfile administratorProfile;

}
