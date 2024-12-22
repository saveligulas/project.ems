package fhv.team11.project.ems.user.entity;

import fhv.team11.project.ems.admin.AdministratorProfileEntity;
import fhv.team11.project.ems.customer.CustomerProfileEntity;
import fhv.team11.project.ems.organization.EventOrganizerProfileEntity;
import fhv.team11.project.ems.backoffice.BackOfficeProfileEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.Nullable;

@Entity
@Setter
public class UserEntityDetails {

    @Id
    @GeneratedValue
    @Getter
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @Getter
    private UserEntity user;

    @Nullable
    @OneToOne(mappedBy = "userEntityDetails", cascade = CascadeType.ALL)
    private CustomerProfileEntity customerProfileEntity;

    @Nullable
    @OneToOne(mappedBy = "userEntityDetails")
    private BackOfficeProfileEntity backOfficeProfile;

    @Nullable
    @OneToOne(mappedBy = "userEntityDetails")
    private AdministratorProfileEntity administratorProfile;

    @Nullable
    @OneToOne(mappedBy = "userEntityDetails")
    private EventOrganizerProfileEntity eventOrganizerProfile;

    public @Nullable CustomerProfileEntity getCustomerProfileEntity() {
        return customerProfileEntity;
    }

    public @Nullable BackOfficeProfileEntity getBackOfficeProfile() {
        return backOfficeProfile;
    }

    public @Nullable AdministratorProfileEntity getAdministratorProfile() {
        return administratorProfile;
    }

    public @Nullable EventOrganizerProfileEntity getEventOrganizerProfile() {
        return eventOrganizerProfile;
    }
}
