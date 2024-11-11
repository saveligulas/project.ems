package fhv.team11.project.ems.commons.home.base.attribute;

import lombok.Data;

@Data
public class UserAttributes {
    private final UserViewModel user;

    public UserAttributes() {
        this.user = new UserViewModel();
    }

    public boolean isAuthenticated() {
        return user.getUsername() != null;
    }
}
