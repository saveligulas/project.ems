package fhv.team11.project.ems.commons.address;

public class AddressDTOMapper {
    public static Address getAddress(AddressDTO dto) {
        Address address = new Address();
        address.setCountry(dto.getCountry());
        address.setRegion(dto.getRegion());
        address.setCity(dto.getCity());
        address.setZip(dto.getZip());
        address.setStreet(dto.getStreet());
        address.setNumber(dto.getHouseNumber());
        address.setOptionalText(dto.getOptionalText());
        return address;
    }
}
