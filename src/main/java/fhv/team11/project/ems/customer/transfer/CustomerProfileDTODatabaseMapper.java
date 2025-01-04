package fhv.team11.project.ems.customer.transfer;

import fhv.team11.project.ems.commons.address.AddressDTODatabaseMapper;
import fhv.team11.project.ems.commons.address.AddressDTOMapperNew;
import fhv.team11.project.ems.commons.database.IPresentationDatabaseMapper;
import fhv.team11.project.ems.customer.CustomerProfileEntity;
import fhv.team11.project.ems.domain.user.CustomerProfile;

public class CustomerProfileDTODatabaseMapper implements IPresentationDatabaseMapper<CustomerProfileDTO, CustomerProfileEntity> {
    public static final CustomerProfileDTODatabaseMapper INSTANCE = new CustomerProfileDTODatabaseMapper();

    private CustomerProfileDTODatabaseMapper() {
    }

    @Override
    public CustomerProfileDTO getView(CustomerProfileEntity entity) {
        if (entity == null) {
            return null;
        }

        CustomerProfileDTO dto = new CustomerProfileDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setSecret(entity.getSecret());
        dto.setAddress(AddressDTODatabaseMapper.INSTANCE.getView(entity.getAddressEntity()));

        return dto;
    }
}
