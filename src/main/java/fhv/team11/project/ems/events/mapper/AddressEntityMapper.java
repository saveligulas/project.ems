package fhv.team11.project.ems.events.mapper;

import fhv.team11.project.ems.commons.address.Address;
import fhv.team11.project.ems.domain.adress.AddressModel;
import fhv.team11.project.ems.domain.commons.exception.DomainFieldValidationException;
import fhv.team11.project.ems.domain.events.ActiveEventDateModel;
import fhv.team11.project.ems.domain.events.TemplateModel;
import fhv.team11.project.ems.events.repo.EventDate;
import fhv.team11.project.ems.events.transfer.EventTemplateDTO;

public class AddressEntityMapper {

    public static final AddressEntityMapper INSTANCE = new AddressEntityMapper();

    public Address toEntity(AddressModel addressModel) {
        Address address = new Address();

        address.setId(addressModel.getId());
        address.setCity(addressModel.getCity());
        address.setCountry(addressModel.getCountry());
        address.setRegion(addressModel.getRegion());
        address.setStreet(addressModel.getStreet());
        address.setZip(addressModel.getZip());
        address.setHouseNumber(addressModel.getHouseNumber());
        address.setOptionalText(addressModel.getOptionalText());

        return address;
    }

    public AddressModel toModel(Address address) throws DomainFieldValidationException {
        return new AddressModel(address.getId(), address.getCountry(), address.getRegion(), address.getCity(), address.getZip(), address.getStreet(), address.getHouseNumber(), address.getOptionalText());
    }
}
