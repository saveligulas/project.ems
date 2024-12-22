package fhv.team11.project.ems.customer.transfer;

import fhv.team11.project.ems.commons.address.AddressDTOMapperImpl;
import fhv.team11.project.ems.commons.database.IDTOEntityBiMapper;
import fhv.team11.project.ems.customer.CustomerProfileEntity;
import org.jspecify.annotations.Nullable;

public class CustomerProfileDTOMapper implements IDTOEntityBiMapper<CustomerProfileEntity, CustomerProfileDTO> {

    public static final CustomerProfileDTOMapper INSTANCE = new CustomerProfileDTOMapper();

    @Override
    public CustomerProfileEntity getEntity(CustomerProfileDTO dto) {
        CustomerProfileEntity customerProfileEntity = new CustomerProfileEntity();

        customerProfileEntity.setAddress(AddressDTOMapperImpl.INSTANCE.toEntity(dto.getAddress()));
        customerProfileEntity.setFirstName(dto.getFirstName());
        customerProfileEntity.setLastName(dto.getLastName());
        customerProfileEntity.setPhoneNumber(dto.getPhoneNumber());
        customerProfileEntity.setSecret(dto.getSecret());

        return customerProfileEntity;
    }

    @Override
    public @Nullable CustomerProfileDTO getDTO(@Nullable CustomerProfileEntity entity) {
        if (entity == null) {
            return null;
        }
        CustomerProfileDTO customerProfileDTO = new CustomerProfileDTO();

        customerProfileDTO.setAddress(AddressDTOMapperImpl.INSTANCE.toDTO(entity.getAddress()));
        customerProfileDTO.setFirstName(entity.getFirstName());
        customerProfileDTO.setLastName(entity.getLastName());
        customerProfileDTO.setPhoneNumber(entity.getPhoneNumber());
        customerProfileDTO.setSecret(entity.getSecret());

        return customerProfileDTO;
    }
}
