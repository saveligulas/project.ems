package fhv.team11.project.ems.commons.controller.base.attribute.user;

import fhv.team11.project.ems.customer.transfer.CustomerProfileDTO;
import fhv.team11.project.ems.security.permission.role.Role;
import jakarta.validation.constraints.Null;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NullMarked
public class UserAttributes {
    private static final List<Role> ROLES = Arrays.asList(Role.values());

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
            throw new IllegalStateException("Username can't be accessed if User is not authenticated");
        }
        return attributes.username;
    }

    public boolean hasCustomerProfile() {
        return attributes.customerProfileDTO != null;
    }

    public CustomerProfileDTO getCustomerProfileDTO() {
        if (attributes.customerProfileDTO == null) {
            throw new IllegalStateException("Customer profile not set");
        }
        return attributes.customerProfileDTO;
    }
}
