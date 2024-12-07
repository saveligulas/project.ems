package fhv.team11.project.ems.user.entity;

import fhv.team11.project.ems.admin.AdministratorProfile;
import fhv.team11.project.ems.customer.CustomerProfile;
import fhv.team11.project.ems.organization.EventOrganizerProfile;
import fhv.team11.project.ems.backoffice.BackOfficeProfile;
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
    private CustomerProfile customerProfile;

    @Nullable
    @OneToOne(mappedBy = "userEntityDetails")
    private BackOfficeProfile backOfficeProfile;

    @Nullable
    @OneToOne(mappedBy = "userEntityDetails")
    private AdministratorProfile administratorProfile;

    @Nullable
    @OneToOne(mappedBy = "userEntityDetails")
    private EventOrganizerProfile eventOrganizerProfile;

    public @Nullable CustomerProfile getCustomerProfile() {
        return customerProfile;
    }

    public @Nullable BackOfficeProfile getBackOfficeProfile() {
        return backOfficeProfile;
    }

    public @Nullable AdministratorProfile getAdministratorProfile() {
        return administratorProfile;
    }

    public @Nullable EventOrganizerProfile getEventOrganizerProfile() {
        return eventOrganizerProfile;
    }
}
