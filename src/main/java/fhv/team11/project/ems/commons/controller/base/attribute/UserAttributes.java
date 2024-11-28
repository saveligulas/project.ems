package fhv.team11.project.ems.commons.controller.base.attribute;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserAttributes {
    private final UserViewModel user;
    private final List<String> permissions = new ArrayList<>();

    public UserAttributes() {
        this.user = new UserViewModel();
    }

    public boolean isAuthenticated() {
        return user.getUsername() != null;
    }

    public boolean hasPermission(String permission) {
        return permissions.contains(permission);
    }
}
