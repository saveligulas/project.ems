package fhv.team11.project.ems.commons.controller.base.attribute.user;

import fhv.team11.project.ems.backoffice.transfer.BackOfficeProfileDTO;
import fhv.team11.project.ems.customer.transfer.CustomerProfileDTO;
import fhv.team11.project.ems.security.error.SecuredEndpointAccessException;
import fhv.team11.project.ems.security.jwt.JwtSecurityContextHolder;
import fhv.team11.project.ems.security.permission.role.Role;
import org.jspecify.annotations.NullMarked;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NullMarked
public class UserAttributes {
    private static final List<Role> ROLES = Arrays.asList(Role.values());

    private final UserAttributeDTO attributes;
    private final List<Role> roles;
    private final List<String> permissions;

    public UserAttributes() {
        this.attributes = new UserAttributeDTO();

        List<Role> roles = new ArrayList<>();
        try {
            roles = JwtSecurityContextHolder.getUserJDBC().getRoles().stream().toList();
        } catch (SecuredEndpointAccessException ignored) {

        }
        this.roles = roles;

        this.permissions = new ArrayList<>();
    }

    public boolean isAuthenticated() {
        return attributes.username != null;
    }

    public boolean hasRole(Role role) {
        return roles.contains(role);
    }

    public boolean hasRole(String roleName) {
        return hasRole(Role.valueOf(roleName));
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

    public boolean hasBackOfficeProfile() {
        return attributes.backOfficeProfileDTO != null;
    }

    public BackOfficeProfileDTO getBackOfficeProfileDTO() {
        if (attributes.backOfficeProfileDTO == null) {
            throw new IllegalStateException("BackOffice profile not set");
        }
        return attributes.backOfficeProfileDTO;
    }
}
