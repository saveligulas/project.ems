package fhv.team11.project.ems.security.jwt;

import fhv.team11.project.ems.commons.user.repo.User;
import fhv.team11.project.ems.commons.user.repo.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class JwtSecurityContextHolder {

    public static User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserEntity) {
                return new User(((UserEntity) principal).getId());
            }
        }
        return null;
    }
}
