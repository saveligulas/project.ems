package fhv.team11.project.ems.commons.controller.base.attribute;

import fhv.team11.project.ems.security.error.SecuredEndpointAccessException;
import fhv.team11.project.ems.security.jwt.JwtSecurityContextHolder;
import lombok.Data;

@Data
public class UserViewModel {
    private final String username;

    public UserViewModel() {
        String name;
        try {
            name = JwtSecurityContextHolder.getUserEntity().getUsername();
        } catch (SecuredEndpointAccessException e) {
            name = null;
        }
        this.username = name;
    }
}
