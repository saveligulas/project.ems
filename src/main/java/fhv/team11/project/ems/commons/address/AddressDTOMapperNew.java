package fhv.team11.project.ems.commons.address;

import fhv.team11.project.ems.commons.mapper.IBiPresentationDomainMapper;
import fhv.team11.project.ems.commons.validation.ValidationExceptionDecorator;
import fhv.team11.project.ems.domain.adress.Address;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;

public class AddressDTOMapperNew implements IBiPresentationDomainMapper<AddressDTO, Address> {
    public static final AddressDTOMapperNew INSTANCE = new AddressDTOMapperNew();

    private AddressDTOMapperNew() {

    }

    public Address getDomainDecorated(AddressDTO presentationObject) throws DomainValidationException {
        try {
            return getDomain(presentationObject);
        } catch (DomainValidationException e) {
            ValidationExceptionDecorator.encapsulateFieldErrorsTo("address", e);
        }
        throw new RuntimeException("Unexpected Logic occurred");
    }

    @Override
    public AddressDTO getView(Address domain) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCity(domain.getCity());
        addressDTO.setCountry(domain.getCountry());
        addressDTO.setHouseNumber(domain.getHouseNumber());
        addressDTO.setOptionalText(domain.getOptionalText());
        addressDTO.setRegion(domain.getRegion());
        addressDTO.setStreet(domain.getStreet());
        addressDTO.setZip(domain.getZip());

        return addressDTO;
    }

    @Override
    public Address getDomain(AddressDTO presentationObject) throws DomainValidationException {
        return new Address(
                null,
                presentationObject.getCountry(),
                presentationObject.getRegion(),
                presentationObject.getCity(),
                presentationObject.getZip(),
                presentationObject.getStreet(),
                presentationObject.getHouseNumber(),
                presentationObject.getOptionalText()
        );
    }
}
