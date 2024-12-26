package fhv.team11.project.ems.domain.user;

import fhv.team11.project.ems.domain.commons.*;
import fhv.team11.project.ems.domain.commons.exception.DomainFieldException;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.security.permission.role.Role;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;

@NullMarked
@Getter
public class User implements IDomainObject {
    private final DomainObjectConstructorHelper constructorHelper;

    @Nullable
    private Long id;
    private String email;
    private String password;
    private String username;

    private List<Role> roles;
    private List<String> grantedPermissions;

    @Nullable
    private CustomerProfile customerProfile;
    @Nullable
    private BackOfficeProfile backOfficeProfile;
    @Nullable
    private AdministratorProfile administratorProfile;
    @Nullable
    private EventOrganizerProfile eventOrganizerProfile;

    public User(@Nullable Long id,
                String email,
                String password,
                String username,
                List<Role> roles,
                List<String> grantedPermissions,
                @Nullable CustomerProfile customerProfile,
                @Nullable BackOfficeProfile backOfficeProfile,
                @Nullable AdministratorProfile administratorProfile,
                @Nullable EventOrganizerProfile eventOrganizerProfile
    ) throws DomainValidationException {
        this.constructorHelper = new DomainObjectConstructorHelper();

        this.setId(id);
        this.setEmail(email);
        this.setPassword(password);
        this.setUsername(username);
        this.setRoles(roles);
        this.setGrantedPermissions(grantedPermissions);
        this.setCustomerProfile(customerProfile);
        this.setBackOfficeProfile(backOfficeProfile);
        this.setAdministratorProfile(administratorProfile);
        this.setEventOrganizerProfile(eventOrganizerProfile);

        this.constructorHelper.finish();
    }

    private void setId(@Nullable Long id) throws DomainFieldException {
        String fieldName = "id";
        String errorMessage;

        if (!IdValidator.isValid(id, constructorHelper)) {
            errorMessage = "ID is invalid";
            handleError(fieldName, errorMessage, constructorHelper);
        }

        this.id = id;
    }

    public void setEmail(String email) throws DomainFieldException {
        String fieldName = "email";
        String errorMessage;

        if (!EmailValidator.isValid(email)) {
            errorMessage = "Email is invalid";
            handleError(fieldName, errorMessage, constructorHelper);
        }

        this.email = email;
    }

    public void setPassword(String password) throws DomainFieldException {
        String fieldName = "password";
        String errorMessage;

        if (!PasswordValidator.isValid(password)) {
            errorMessage ="Password must contain min.: 8 letters, 1 uppercase, 1 digit";
            handleError(fieldName, errorMessage, constructorHelper);
        }

        this.password = password;
    }

    public void setUsername(String username) throws DomainFieldException {
        String fieldName = "username";
        String errorMessage;

        if (!StringValidator.isValid(username, 3)) {
            errorMessage ="Username is invalid";
            handleError(fieldName, errorMessage, constructorHelper);
        }

        this.username = username;
    }

    public void setRoles(List<Role> roles) throws DomainFieldException {
        String fieldName = "roles";
        String errorMessage;

        if (roles.isEmpty()) {
            errorMessage = "Roles is empty";
            handleError(fieldName, errorMessage, constructorHelper);
        }

        this.roles = roles;
    }

    public void setGrantedPermissions(List<String> grantedPermissions) {
        this.grantedPermissions = grantedPermissions;
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
