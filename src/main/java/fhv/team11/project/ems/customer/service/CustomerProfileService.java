package fhv.team11.project.ems.customer.service;

import fhv.team11.project.ems.customer.CustomerProfileEntityEntity;
import fhv.team11.project.ems.customer.CustomerProfileRepository;
import fhv.team11.project.ems.customer.transfer.CustomerProfileDTO;
import fhv.team11.project.ems.customer.transfer.CustomerProfileDTOMapper;
import fhv.team11.project.ems.security.jwt.JwtSecurityContextHolder;
import fhv.team11.project.ems.security.permission.role.Role;
import fhv.team11.project.ems.user.entity.UserEntity;
import fhv.team11.project.ems.user.entity.UserEntityRepository;
import fhv.team11.project.ems.user.entity.UserJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerProfileService {

    private final CustomerProfileRepository customerProfileRepository;
    private final UserEntityRepository userEntityRepository;

    @Autowired
    public CustomerProfileService(CustomerProfileRepository customerProfileRepository,
                                  UserEntityRepository userEntityRepository) {
        this.customerProfileRepository = customerProfileRepository;
        this.userEntityRepository = userEntityRepository;
    }

    public void createNewCustomerProfile(CustomerProfileDTO customerProfileDTO) {
        UserJDBC userJDBC = JwtSecurityContextHolder.getUserJDBC();
        UserEntity user = JwtSecurityContextHolder.getUser();
        CustomerProfileEntityEntity customerProfileEntity = CustomerProfileDTOMapper.INSTANCE.getEntity(customerProfileDTO);
        if (userJDBC.getRoles().contains(Role.CUSTOMER)) {
            customerProfileEntity.setUserEntityDetails(user.getUserEntityDetails());
            user.getUserEntityDetails().setCustomerProfileEntity(customerProfileEntity);
            userEntityRepository.save(user);
        } else {
            customerProfileRepository.save(customerProfileEntity);
        }
    }

    public List<CustomerProfileDTO> getListOfCustomerProfiles(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<CustomerProfileEntityEntity> page = customerProfileRepository.findAll(pageable);
        return page.getContent().stream()
                .map(CustomerProfileDTOMapper.INSTANCE::getDTO)
                .toList();
    }
}
