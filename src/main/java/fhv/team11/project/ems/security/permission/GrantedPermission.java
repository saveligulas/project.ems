package fhv.team11.project.ems.security.permission;

public class GrantedPermission implements AuthenticationPermission {

    private final String grantedPermission;

    public GrantedPermission(String grantedPermission) {
        this.grantedPermission = grantedPermission;
    }

    public GrantedPermission(Object grantedPermission) {
        this.grantedPermission = grantedPermission.toString();
    }

    @Override
    public String getPermission() {
        return grantedPermission;
    }

    public static GrantedPermission of(Object obj) {
        return new GrantedPermission(obj.toString());
    }
}
