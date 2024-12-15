package fhv.team11.project.ems.domain.user;

import fhv.team11.project.ems.commons.domain.DomainFieldValidationException;
import fhv.team11.project.ems.commons.domain.DomainInstantiationException;
import fhv.team11.project.ems.commons.domain.IDomainObject;
import fhv.team11.project.ems.domain.commons.EmailValidator;
import fhv.team11.project.ems.domain.commons.PasswordValidator;
import fhv.team11.project.ems.security.permission.role.Role;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.List;

@NullMarked
public class User implements IDomainObject {
    @Nullable
    private transient HashMap<String, String> fieldErrors;

    private String email;
    private String password;
    private String username;

    @Nullable
    private UserProfile userProfile;
    private List<Role> roles;
    private List<String> grantedPermissions;

    public User(String email, String password, String username, UserProfile userProfile, List<Role> roles, List<String> grantedPermissions) throws DomainFieldValidationException {
        fieldErrors = new HashMap<>();

        this.setEmail(email);
        this.setPassword(password);
        this.setUsername(username);
        this.setUserProfile(userProfile);
        this.setRoles(roles);
        this.setGrantedPermissions(grantedPermissions);

        checkFieldErrors();
    }

    public void setEmail(String email) throws DomainFieldValidationException {
        String fieldName = "email";
        String errorMessage = "Email is invalid";

        if (!EmailValidator.isValid(email)) {
            if (this.isInstantiated()) {
                throw new DomainFieldValidationException(fieldName, errorMessage);
            } else {
                fieldErrors.put(fieldName, errorMessage);
            }
        }

        this.email = email;
    }

    public void setPassword(String password) throws DomainFieldValidationException {
        String fieldName = "password";
        String errorMessage = "Password must contain min.: 8 letters, 1 uppercase, 1 digit";

        if (!PasswordValidator.isValid(password)) {
            if (this.isInstantiated()) {
                throw new DomainFieldValidationException(fieldName, errorMessage);
            }
        }

        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setGrantedPermissions(List<String> grantedPermissions) {
        this.grantedPermissions = grantedPermissions;
    }

    @Override
    public void checkFieldErrors() throws DomainInstantiationException {
        if (fieldErrors != null && !fieldErrors.isEmpty()) {
            throw new DomainInstantiationException(fieldErrors);
        }
        fieldErrors = null;
    }

    @Override
    public boolean isInstantiated() {
        return fieldErrors == null;
    }
}
