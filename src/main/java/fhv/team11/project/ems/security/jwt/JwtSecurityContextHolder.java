package fhv.team11.project.ems.security.jwt;

import fhv.team11.project.ems.user.repo.entity.User;
import fhv.team11.project.ems.user.repo.entity.UserJDBC;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class JwtSecurityContextHolder {

    public static User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserJDBC) {
                return new User(((UserJDBC) principal).getId());
            }
        }
        return null;
    }
}
