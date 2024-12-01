package fhv.team11.project.ems.commons.controller.base.attribute.user;

import fhv.team11.project.ems.customer.transfer.CustomerProfileDTO;
import fhv.team11.project.ems.customer.transfer.CustomerProfileDTOMapper;
import fhv.team11.project.ems.security.error.SecuredEndpointAccessException;
import fhv.team11.project.ems.security.jwt.JwtSecurityContextHolder;
import fhv.team11.project.ems.user.entity.UserEntity;
import fhv.team11.project.ems.user.entity.UserJDBC;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;

@Slf4j
@Getter(AccessLevel.PACKAGE)
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
