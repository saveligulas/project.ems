package fhv.team11.project.ems.commons.address;

import fhv.team11.project.ems.commons.database.IPresentationDatabaseMapper;

public class AddressDTODatabaseMapper implements IPresentationDatabaseMapper<AddressDTO, AddressEntity> {
    public static final AddressDTODatabaseMapper INSTANCE = new AddressDTODatabaseMapper();

    private AddressDTODatabaseMapper() {
    }

    @Override
    public AddressDTO getView(AddressEntity entity) {
        AddressDTO dto = new AddressDTO();

        dto.setCountry(entity.getCountry());
        dto.setCity(entity.getCity());
        dto.setHouseNumber(entity.getHouseNumber());
        dto.setOptionalText(entity.getOptionalText());
        dto.setRegion(entity.getRegion());
        dto.setStreet(entity.getStreet());
        dto.setZip(entity.getZip());

        return dto;
    }
}
