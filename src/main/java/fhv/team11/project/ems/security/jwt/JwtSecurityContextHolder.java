package fhv.team11.project.ems.security.jwt;

import fhv.team11.project.ems.security.error.SecuredEndpointAccessException;
import fhv.team11.project.ems.user.repo.entity.User;
import fhv.team11.project.ems.user.repo.entity.UserJDBC;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class JwtSecurityContextHolder {

    //TODO: add method to get User Domain Model with different Profiles
    public static User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserJDBC) {
                //TODO: change this to fetch user from repository to get customerProfile
                return new User(((UserJDBC) principal).getId(), null);
            }
        }
        throw new SecuredEndpointAccessException();
    }

    public static UserJDBC getUserEntity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserJDBC) {
                return (UserJDBC) principal;
            }
        }
        throw new SecuredEndpointAccessException();
    }
}
