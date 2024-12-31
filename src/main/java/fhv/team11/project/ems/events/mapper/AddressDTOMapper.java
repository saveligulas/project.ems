package fhv.team11.project.ems.events.mapper;

import fhv.team11.project.ems.commons.address.AddressDTO;
import fhv.team11.project.ems.domain.adress.Address;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;

import javax.annotation.Nullable;

public class AddressDTOMapper {

    public static final AddressDTOMapper INSTANCE = new AddressDTOMapper();

    public AddressDTO toDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setRegion(address.getRegion());
        addressDTO.setZip(address.getZip());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setHouseNumber(address.getHouseNumber());
        addressDTO.setOptionalText(address.getOptionalText());
        addressDTO.setCity(address.getCity());
        return addressDTO;
    }

    public Address toModel(AddressDTO addressDTO, @Nullable Long addressid) throws DomainValidationException {
        return new Address(addressid, addressDTO.getCountry(), addressDTO.getRegion(), addressDTO.getCity(), addressDTO.getZip(), addressDTO.getStreet(), addressDTO.getHouseNumber(), addressDTO.getOptionalText());
    }
}
