package fhv.team11.project.ems.commons.controller.base.attribute;

import fhv.team11.project.ems.customer.CustomerProfile;
import fhv.team11.project.ems.customer.transfer.CustomerProfileDTO;
import fhv.team11.project.ems.customer.transfer.CustomerProfileDTOMapper;
import fhv.team11.project.ems.security.error.SecuredEndpointAccessException;
import fhv.team11.project.ems.security.jwt.JwtSecurityContextHolder;
import fhv.team11.project.ems.security.permission.annotation.RequiresPermission;
import fhv.team11.project.ems.user.entity.UserEntity;
import fhv.team11.project.ems.user.entity.UserJDBC;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.NullUnmarked;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

@Slf4j
public class UserAttributeDTO {
    @Nullable
    final String username;
    @Nullable
    final CustomerProfileDTO customerProfileDTO;

    public UserAttributeDTO() {
        String name = null;
        CustomerProfileDTO customerProfile = null;
        try {
            UserEntity user = JwtSecurityContextHolder.getUser();
            UserJDBC userJDBC = JwtSecurityContextHolder.getUserJDBC();
            name = userJDBC.getUsername();
            if (user.getUserEntityDetails() == null) {
                log.info("User has not set their UserEntityDetails");
            } else {
                customerProfile = CustomerProfileDTOMapper.INSTANCE.getDTO(user.getUserEntityDetails().getCustomerProfile());
            }
        } catch (SecuredEndpointAccessException e) {
            log.info("User is not logged in");
        }
        this.username = name;
        this.customerProfileDTO = customerProfile;
    }
}
