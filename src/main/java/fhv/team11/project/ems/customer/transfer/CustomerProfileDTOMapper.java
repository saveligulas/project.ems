package fhv.team11.project.ems.customer.transfer;

import fhv.team11.project.ems.commons.database.IDTOEntityBiMapper;
import fhv.team11.project.ems.customer.CustomerProfile;

public class CustomerProfileDTOMapper implements IDTOEntityBiMapper<CustomerProfile, CustomerProfileDTO> {

    public static final CustomerProfileDTOMapper INSTANCE = new CustomerProfileDTOMapper();

    @Override
    public CustomerProfile getEntity(CustomerProfileDTO dto) {
        return null;
    }

    @Override
    public CustomerProfileDTO getDTO(CustomerProfile entity) {
        return null;
    }
}
