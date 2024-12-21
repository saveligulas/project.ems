package fhv.team11.project.ems.domain.model.user;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public class UserDetails {
    private Long id;
    @Nullable
    private CustomerProfile customerProfile;
    @Nullable
    private BackOfficeProfile backOfficeProfile;
    @Nullable
    private AdministratorProfile administratorProfile;
    @Nullable
    private EventOrganizerProfile eventOrganizerProfile;

    public UserDetails(Long id) {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerProfile(@Nullable CustomerProfile customerProfile) {
        this.customerProfile = customerProfile;
    }

    public void setBackOfficeProfile(@Nullable BackOfficeProfile backOfficeProfile) {
        this.backOfficeProfile = backOfficeProfile;
    }

    public void setAdministratorProfile(@Nullable AdministratorProfile administratorProfile) {
        this.administratorProfile = administratorProfile;
    }

    public void setEventOrganizerProfile(@Nullable EventOrganizerProfile eventOrganizerProfile) {
        this.eventOrganizerProfile = eventOrganizerProfile;
    }
}
