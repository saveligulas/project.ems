package fhv.team11.project.ems.events.mapper;

import fhv.team11.project.ems.commons.address.AddressEntity;
import fhv.team11.project.ems.domain.adress.Address;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;

public class AddressEntityMapper {

    public static final AddressEntityMapper INSTANCE = new AddressEntityMapper();

    public AddressEntity toEntity(Address addressModel) {
        AddressEntity addressEntity = new AddressEntity();

        addressEntity.setId(addressModel.getId());
        addressEntity.setCity(addressModel.getCity());
        addressEntity.setCountry(addressModel.getCountry());
        addressEntity.setRegion(addressModel.getRegion());
        addressEntity.setStreet(addressModel.getStreet());
        addressEntity.setZip(addressModel.getZip());
        addressEntity.setHouseNumber(addressModel.getHouseNumber());
        addressEntity.setOptionalText(addressModel.getOptionalText());

        return addressEntity;
    }

    public Address toModel(AddressEntity addressEntity) throws DomainValidationException {
        return new Address(addressEntity.getId(), addressEntity.getCountry(), addressEntity.getRegion(), addressEntity.getCity(), addressEntity.getZip(), addressEntity.getStreet(), addressEntity.getHouseNumber(), addressEntity.getOptionalText());
    }
}
