package fhv.team11.project.ems.customer.transfer;

import fhv.team11.project.ems.commons.address.AddressDTOMapper;
import fhv.team11.project.ems.commons.address.AddressDTOMapperImpl;
import fhv.team11.project.ems.commons.address.AddressDTOMapperNew;
import fhv.team11.project.ems.commons.mapper.IBiPresentationDomainMapper;
import fhv.team11.project.ems.customer.CustomerProfileEntity;
import fhv.team11.project.ems.domain.adress.Address;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.user.CustomerProfile;
import org.jspecify.annotations.Nullable;

public class CustomerProfileDTOMapper implements IBiPresentationDomainMapper<CustomerProfileDTO, CustomerProfile> {
    public static final CustomerProfileDTOMapper INSTANCE = new CustomerProfileDTOMapper();

    private CustomerProfileDTOMapper() {
    }


    @Override
    public CustomerProfileDTO getView(CustomerProfile domain) {
        if (domain == null) {
            return null;
        }

        CustomerProfileDTO dto = new CustomerProfileDTO();
        dto.setId(domain.getId());
        dto.setFirstName(domain.getFirstName());
        dto.setLastName(domain.getLastName());
        dto.setPhoneNumber(domain.getPhoneNumber());
        dto.setSecret(domain.getSecret());
        dto.setAddress(AddressDTOMapperNew.INSTANCE.getView(domain.getAddress()));

        return dto;
    }

    @Override
    public CustomerProfile getDomain(CustomerProfileDTO presentationObject) throws DomainValidationException {
        if (presentationObject == null) {
            return null;
        }

        return new CustomerProfile(
                presentationObject.getId(),
                presentationObject.getSecret(),
                presentationObject.getPhoneNumber(),
                AddressDTOMapperNew.INSTANCE.getDomainDecorated(presentationObject.getAddress()),
                presentationObject.getLastName(),
                presentationObject.getFirstName()
        );
    }
}
