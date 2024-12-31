package fhv.team11.project.ems.customer;

import fhv.team11.project.ems.commons.address.AddressDomainDatabaseFactory;
import fhv.team11.project.ems.commons.address.AddressEntity;
import fhv.team11.project.ems.commons.domain.DomainDatabaseFactory;
import fhv.team11.project.ems.commons.domain.IHandleDomainPersistence;
import fhv.team11.project.ems.commons.domain.ISimpleDomainDatabaseMapper;
import fhv.team11.project.ems.commons.validation.domain.handler.HandleBindingResultException;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.user.CustomerProfile;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerProfileDomainDatabaseFactory extends DomainDatabaseFactory implements HandleBindingResultException, ISimpleDomainDatabaseMapper<CustomerProfile, CustomerProfileEntity>, IHandleDomainPersistence<CustomerProfile> {

    private final AddressDomainDatabaseFactory addressDomainDatabaseFactory;

    @Autowired
    public CustomerProfileDomainDatabaseFactory(AddressDomainDatabaseFactory addressDomainDatabaseFactory) {
        this.addressDomainDatabaseFactory = addressDomainDatabaseFactory;
    }

    @Override
    public CustomerProfile toDomain(@Nullable CustomerProfileEntity entity) throws DomainValidationException {
        if (entity == null) {
            return null;
        }

        CustomerProfile customerProfile = new CustomerProfile(
                entity.getId(),
                entity.getSecret(),
                entity.getPhoneNumber(),
                addressDomainDatabaseFactory.toDomain(entity.getAddressEntity()),
                entity.getFirstName(),
                entity.getLastName());


        return customerProfile;
    }

    @Override
    public CustomerProfileEntity toNotPersistedEntity(CustomerProfile customerProfile) {
        CustomerProfileEntity customerProfileEntity = new CustomerProfileEntity();

        customerProfileEntity.setFirstName(customerProfile.getFirstName());
        customerProfileEntity.setLastName(customerProfile.getLastName());
        customerProfileEntity.setPhoneNumber(customerProfile.getPhoneNumber());
        customerProfileEntity.setSecret(customerProfile.getSecret());
        customerProfileEntity.setAddressEntity(addressDomainDatabaseFactory.toNotPersistedEntity(customerProfile.getAddress()));

        return customerProfileEntity;
    }

    @Override
    public CustomerProfile persist(CustomerProfile domainObject) {
        return null;
    }
}
