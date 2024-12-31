package fhv.team11.project.ems.commons.address;

import fhv.team11.project.ems.commons.domain.DomainDatabaseFactory;
import fhv.team11.project.ems.commons.domain.IHandleDomainPersistence;
import fhv.team11.project.ems.commons.domain.ISimpleDomainDatabaseMapper;
import fhv.team11.project.ems.domain.adress.Address;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import org.springframework.stereotype.Component;

@Component
public class AddressDomainDatabaseFactory extends DomainDatabaseFactory implements ISimpleDomainDatabaseMapper<Address, AddressEntity>, IHandleDomainPersistence<Address> {
    @Override
    public Address persist(Address domainObject) throws DomainValidationException {
        return null;
    }

    @Override
    public AddressEntity toNotPersistedEntity(Address domain) {
        return null;
    }

    @Override
    public Address toDomain(AddressEntity entity) {
        return null;
    }
}
