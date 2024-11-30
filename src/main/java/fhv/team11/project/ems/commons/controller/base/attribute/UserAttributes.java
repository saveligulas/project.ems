package fhv.team11.project.ems.commons.controller.base.attribute;

import lombok.Data;
import org.jspecify.annotations.NullMarked;
import java.util.ArrayList;
import java.util.List;

@NullMarked
public class UserAttributes {
    private final UserAttributeDTO attributes;
    private final List<String> permissions;

    public UserAttributes() {
        this.attributes = new UserAttributeDTO();
        this.permissions = new ArrayList<>();
    }

    public boolean isAuthenticated() {
        return attributes.username != null;
    }

    public boolean hasPermission(String permission) {
        return permissions.contains(permission);
    }

    public String getUsername() {
        if (attributes.username == null) {
            throw new IllegalArgumentException("Username can't be accessed if User is not authenticated");
        }
        return attributes.username;
    }
}
