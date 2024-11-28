package fhv.team11.project.ems.customer.service;

import fhv.team11.project.ems.customer.CustomerProfile;
import fhv.team11.project.ems.customer.CustomerProfileRepository;
import fhv.team11.project.ems.customer.transfer.CustomerProfileDTO;
import fhv.team11.project.ems.customer.transfer.CustomerProfileDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerProfileService {

    private final CustomerProfileRepository customerProfileRepository;

    @Autowired
    public CustomerProfileService(CustomerProfileRepository customerProfileRepository) {
        this.customerProfileRepository = customerProfileRepository;
    }

    public void createNewCustomerProfile(CustomerProfileDTO customerProfileDTO) {
        customerProfileRepository.save(CustomerProfileDTOMapper.INSTANCE.getEntity(customerProfileDTO));
    }

    public List<CustomerProfileDTO> getListOfCustomerProfiles(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<CustomerProfile> page = customerProfileRepository.findAll(pageable);
        return page.getContent().stream()
                .map(CustomerProfileDTOMapper.INSTANCE::getDTO)
                .toList();
    }
}
