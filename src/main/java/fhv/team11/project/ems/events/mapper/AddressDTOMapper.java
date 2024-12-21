package fhv.team11.project.ems.events.mapper;

import fhv.team11.project.ems.commons.address.AddressDTO;
import fhv.team11.project.ems.domain.adress.AddressModel;
import fhv.team11.project.ems.domain.commons.exception.DomainFieldValidationException;
import fhv.team11.project.ems.domain.events.TemplateModel;
import fhv.team11.project.ems.events.transfer.EventTemplateDTO;

import javax.annotation.Nullable;

public class AddressDTOMapper {

    public static final AddressDTOMapper INSTANCE = new AddressDTOMapper();

    public AddressDTO toDTO(AddressModel addressModel) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setRegion(addressModel.getRegion());
        addressDTO.setZip(addressModel.getZip());
        addressDTO.setStreet(addressModel.getStreet());
        addressDTO.setHouseNumber(addressModel.getHouseNumber());
        addressDTO.setOptionalText(addressModel.getOptionalText());
        addressDTO.setCity(addressModel.getCity());
        return addressDTO;
    }

    public AddressModel toModel(AddressDTO addressDTO,@Nullable Long addressid) throws DomainFieldValidationException {
        return new AddressModel(addressid, addressDTO.getCountry(), addressDTO.getRegion(), addressDTO.getCity(), addressDTO.getZip(), addressDTO.getStreet(), addressDTO.getHouseNumber(), addressDTO.getOptionalText());
    }
}
