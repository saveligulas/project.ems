package fhv.team11.project.ems.security.permission.role;

import fhv.team11.project.ems.security.permission.GrantedPermission;
import fhv.team11.project.ems.security.permission.UserPermission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RolePermissions {

    private static final List<Role> ROLES = List.of(Role.values());
    private static final HashMap<Role, List<GrantedPermission>> ROLE_PERMISSIONS = new HashMap<>();

    public static List<GrantedPermission> getPermissions(Role role) {
        if (ROLE_PERMISSIONS.isEmpty()) {
            initializePermissions();
        }
        return ROLE_PERMISSIONS.get(role);
    }

    private static void initializePermissions() {
        assignCustomerPermissions();
        assignOrganizerPermissions();
        assignExternalPermissions();
        assignEmployeePermissions();
        assignAdminPermissions();
    }

    private static void assignAdminPermissions() {
        List<GrantedPermission> grantedPermissions = new ArrayList<>();

        grantedPermissions.add(new GrantedPermission(UserPermission.U_ADMIN));

        ROLE_PERMISSIONS.put(Role.ADMIN, grantedPermissions);
    }

    private static void assignEmployeePermissions() {
        List<GrantedPermission> grantedPermissions = new ArrayList<>();

        grantedPermissions.add(new GrantedPermission(UserPermission.U_READ));
        grantedPermissions.add(new GrantedPermission(UserPermission.U_WRITE));

        ROLE_PERMISSIONS.put(Role.EMPLOYEE, grantedPermissions);
    }

    private static void assignExternalPermissions() {
        List<GrantedPermission> grantedPermissions = new ArrayList<>();

        grantedPermissions.add(new GrantedPermission(UserPermission.U_ORG_READ));

        ROLE_PERMISSIONS.put(Role.EXTERNAL, grantedPermissions);
    }

    private static void assignOrganizerPermissions() {
        List<GrantedPermission> grantedPermissions = new ArrayList<>();

        grantedPermissions.add(new GrantedPermission(UserPermission.U_ORG_READ));
        grantedPermissions.add(new GrantedPermission(UserPermission.U_ORG_WRITE));
        grantedPermissions.add(new GrantedPermission(UserPermission.U_ORG_DELETE));

        ROLE_PERMISSIONS.put(Role.ORGANIZER, grantedPermissions);
    }

    private static void assignCustomerPermissions() {
        List<GrantedPermission> grantedPermissions = new ArrayList<>();

        grantedPermissions.add(new GrantedPermission(UserPermission.U_OWN_READ));
        grantedPermissions.add(new GrantedPermission(UserPermission.U_OWN_WRITE));

        ROLE_PERMISSIONS.put(Role.CUSTOMER, grantedPermissions);
    }
}
