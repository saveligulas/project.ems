package fhv.team11.project.ems.security.permission;

public enum UserPermission implements IPermissionEnum {
    U_OWN_READ,
    U_OWN_WRITE,
    U_OWN_DELETE,
    U_ORG_READ,
    U_ORG_WRITE,
    U_ORG_DELETE,
    U_CUS_EXECUTE,
    U_READ,
    U_WRITE,
    U_DELETE,
    U_ADMIN;

    @Override
    public String getPermissionName() {
        return this.toString();
    }
}
