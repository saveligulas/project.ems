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
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserEntityDetails {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToOne(mappedBy = "userEntityDetails")
    private CustomerProfile customerProfile;

    @OneToOne(mappedBy = "userEntityDetails")
    private BackOfficeProfile backOfficeProfile;

    @OneToOne(mappedBy = "userEntityDetails")
    private AdministratorProfile administratorProfile;

    //TODO:
}
