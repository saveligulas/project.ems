package fhv.team11.project.ems.domain.user;

import fhv.team11.project.ems.commons.domain.IDomainObject;
import fhv.team11.project.ems.security.permission.role.Role;
import lombok.Setter;

import java.util.List;

@Setter
public class User implements IDomainObject {
    private final String email;
    private final String password;
    private final String username;
    private final UserProfile userProfile;
    private final List<Role> roles;
    private final List<String> grantedPermissions;

    public User(String email, String password, String username, UserProfile userProfile, List<Role> roles, List<String> grantedPermissions) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.userProfile = userProfile;
        this.roles = roles;
        this.grantedPermissions = grantedPermissions;
    }

    @Override
    public void validate() {

    }
}
