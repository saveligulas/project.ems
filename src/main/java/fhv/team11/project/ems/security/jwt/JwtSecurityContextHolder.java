package fhv.team11.project.ems.security.jwt;

import fhv.team11.project.ems.security.error.SecuredEndpointAccessException;
import fhv.team11.project.ems.user.entity.UserEntity;
import fhv.team11.project.ems.user.entity.UserEntityRepository;
import fhv.team11.project.ems.user.entity.UserJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class JwtSecurityContextHolder {

    private static UserEntityRepository userEntityRepository;

    @Autowired
    public JwtSecurityContextHolder(UserEntityRepository userEntityRepository) {
        JwtSecurityContextHolder.userEntityRepository = userEntityRepository;
    }

    //TODO: add method to get User Domain Model with different Profiles
    public static UserEntity getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserJDBC) {
                Long id = ((UserJDBC) principal).getId();
                return userEntityRepository.findById(id).orElseThrow(SecuredEndpointAccessException::new);
            }
        }
        throw new SecuredEndpointAccessException();
    }

    public static UserJDBC getUserJDBC() {
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
