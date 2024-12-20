package fhv.team11.project.ems.domain.user;

import fhv.team11.project.ems.domain.commons.*;
import fhv.team11.project.ems.domain.commons.exception.DomainFieldValidationException;
import fhv.team11.project.ems.security.permission.role.Role;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;

@NullMarked
public class User implements IDomainObject {
    private final DomainObjectConstructorHelper constructorHelper;

    private Long id;
    private String email;
    private String password;
    private String username;

    @Nullable
    private UserProfile userProfile;
    private List<Role> roles;
    private List<String> grantedPermissions;

    public User(Long id, String email, String password, String username, UserProfile userProfile, List<Role> roles, List<String> grantedPermissions) throws DomainFieldValidationException {
        this.constructorHelper = new DomainObjectConstructorHelper();

        this.setId(id);
        this.setEmail(email);
        this.setPassword(password);
        this.setUsername(username);
        this.setUserProfile(userProfile);
        this.setRoles(roles);
        this.setGrantedPermissions(grantedPermissions);

        this.constructorHelper.finish();
    }

    private void setId(Long id) throws DomainFieldValidationException {
        String fieldName = "id";
        String errorMessage;

        if (!IdValidator.isValid(id)) {
            errorMessage = "ID is invalid";
            handleError(fieldName, errorMessage, constructorHelper);
        }

        this.id = id;
    }

    public void setEmail(String email) throws DomainFieldValidationException {
        String fieldName = "email";
        String errorMessage;

        if (!EmailValidator.isValid(email)) {
            errorMessage = "Email is invalid";
            handleError(fieldName, errorMessage, constructorHelper);
        }

        this.email = email;
    }

    public void setPassword(String password) throws DomainFieldValidationException {
        String fieldName = "password";
        String errorMessage;

        if (!PasswordValidator.isValid(password)) {
            errorMessage ="Password must contain min.: 8 letters, 1 uppercase, 1 digit";
            handleError(fieldName, errorMessage, constructorHelper);
        }

        this.password = password;
    }

    public void setUsername(String username) throws DomainFieldValidationException {
        String fieldName = "username";
        String errorMessage;

        if (!StringValidator.isValid(username, 3)) {
            errorMessage ="Username is invalid";
            handleError(fieldName, errorMessage, constructorHelper);
        }

        this.username = username;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public void setRoles(List<Role> roles) throws DomainFieldValidationException {
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
}
