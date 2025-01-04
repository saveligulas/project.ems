package fhv.team11.project.ems.commons.address;

import com.beust.ah.A;
import fhv.team11.project.ems.commons.domain.DomainDatabaseFactory;
import fhv.team11.project.ems.commons.domain.IHandleDomainPersistence;
import fhv.team11.project.ems.commons.domain.ISimpleDomainDatabaseMapper;
import fhv.team11.project.ems.domain.adress.Address;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import org.springframework.stereotype.Component;

@Component
public class AddressDomainDatabaseFactory extends DomainDatabaseFactory implements ISimpleDomainDatabaseMapper<Address, AddressEntity>{

    @Override
    public AddressEntity toEntity(Address domain) {
        AddressEntity entity = new AddressEntity();

        entity.setId(null);
        entity.setCountry(domain.getCountry());
        entity.setRegion(domain.getRegion());
        entity.setCity(domain.getCity());
        entity.setZip(domain.getZip());
        entity.setStreet(domain.getStreet());
        entity.setHouseNumber(domain.getHouseNumber());
        entity.setOptionalText(domain.getOptionalText());

        return entity;
    }

    @Override
    public Address toDomain(AddressEntity entity) throws DomainValidationException {
        Long id;
        if (entity.getId() == null) {
            id = null;
        } else {
            id = entity.getId();
        }
        return new Address(
                id,
                entity.getCountry(),
                entity.getRegion(),
                entity.getCity(),
                entity.getZip(),
                entity.getStreet(),
                entity.getHouseNumber(),
                entity.getOptionalText()
        );
    }
}
