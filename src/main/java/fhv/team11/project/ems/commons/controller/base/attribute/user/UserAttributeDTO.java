package fhv.team11.project.ems.commons.controller.base.attribute.user;

import fhv.team11.project.ems.backoffice.transfer.BackOfficeProfileDTOMapper;
import fhv.team11.project.ems.customer.transfer.CustomerProfileDTO;
import fhv.team11.project.ems.customer.transfer.CustomerProfileDTOMapper;
import fhv.team11.project.ems.security.error.SecuredEndpointAccessException;
import fhv.team11.project.ems.security.jwt.JwtSecurityContextHolder;
import fhv.team11.project.ems.user.entity.UserEntity;
import fhv.team11.project.ems.user.entity.UserJDBC;
import fhv.team11.project.ems.backoffice.transfer.BackOfficeProfileDTO;
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
    @Nullable
    final BackOfficeProfileDTO backOfficeProfileDTO;

    public UserAttributeDTO() {
        String name = null;
        CustomerProfileDTO customerProfile = null;
        BackOfficeProfileDTO backOfficeProfile = null;

        try {
            UserEntity user = JwtSecurityContextHolder.getUser();
            UserJDBC userJDBC = JwtSecurityContextHolder.getUserJDBC();

            name = userJDBC.getUsername();

            if (user.getUserEntityDetails() == null) {
                log.info("User has not set their UserEntityDetails");
            } else {
                customerProfile = CustomerProfileDTOMapper.INSTANCE.getDTO(user.getUserEntityDetails().getCustomerProfile());
                backOfficeProfile = BackOfficeProfileDTOMapper.INSTANCE.getDTO(user.getUserEntityDetails().getBackOfficeProfile());
            }
        } catch (SecuredEndpointAccessException e) {
            log.info("User is not logged in");
        }

        this.username = name;
        this.customerProfileDTO = customerProfile;
        this.backOfficeProfileDTO = backOfficeProfile;
    }
}
