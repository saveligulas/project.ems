package fhv.team11.project.ems.customer.transfer;

import fhv.team11.project.ems.commons.address.AddressDTOMapperImpl;
import fhv.team11.project.ems.commons.database.IDTOEntityBiMapper;
import fhv.team11.project.ems.customer.CustomerProfile;
import jakarta.validation.constraints.Null;
import org.jspecify.annotations.Nullable;

public class CustomerProfileDTOMapper implements IDTOEntityBiMapper<CustomerProfile, CustomerProfileDTO> {

    public static final CustomerProfileDTOMapper INSTANCE = new CustomerProfileDTOMapper();

    @Override
    public CustomerProfile getEntity(CustomerProfileDTO dto) {
        CustomerProfile customerProfile = new CustomerProfile();

        customerProfile.setAddress(AddressDTOMapperImpl.INSTANCE.toEntity(dto.getAddressDTO()));
        customerProfile.setFirstName(dto.getFirstName());
        customerProfile.setLastName(dto.getLastName());
        customerProfile.setPhoneNumber(dto.getPhoneNumber());
        customerProfile.setSecret(dto.getSecret());

        return customerProfile;
    }

    @Override
    public @Nullable CustomerProfileDTO getDTO(@Nullable CustomerProfile entity) {
        if (entity == null) {
            return null;
        }
        CustomerProfileDTO customerProfileDTO = new CustomerProfileDTO();

        customerProfileDTO.setAddressDTO(AddressDTOMapperImpl.INSTANCE.toDTO(entity.getAddress()));
        customerProfileDTO.setFirstName(entity.getFirstName());
        customerProfileDTO.setLastName(entity.getLastName());
        customerProfileDTO.setPhoneNumber(entity.getPhoneNumber());
        customerProfileDTO.setSecret(entity.getSecret());

        return customerProfileDTO;
    }
}
