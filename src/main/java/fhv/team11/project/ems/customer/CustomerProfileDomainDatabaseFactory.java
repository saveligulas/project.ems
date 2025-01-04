package fhv.team11.project.ems.customer;

import fhv.team11.project.ems.commons.address.AddressDomainDatabaseFactory;
import fhv.team11.project.ems.commons.domain.DomainDatabaseFactory;
import fhv.team11.project.ems.commons.domain.IFindByIdDomainDatabaseMapper;
import fhv.team11.project.ems.commons.domain.IHandleDomainPersistence;
import fhv.team11.project.ems.commons.domain.ISimpleDomainDatabaseMapper;
import fhv.team11.project.ems.commons.error.EntityNotFoundException;
import fhv.team11.project.ems.commons.validation.domain.handler.HandleBindingResultException;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.user.CustomerProfile;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerProfileDomainDatabaseFactory extends DomainDatabaseFactory implements HandleBindingResultException, ISimpleDomainDatabaseMapper<CustomerProfile, CustomerProfileEntity>, IHandleDomainPersistence<CustomerProfile>, IFindByIdDomainDatabaseMapper<CustomerProfile, Long> {

    private final AddressDomainDatabaseFactory addressDomainDatabaseFactory;
    private final CustomerProfileRepository customerProfileRepository;

    @Autowired
    public CustomerProfileDomainDatabaseFactory(AddressDomainDatabaseFactory addressDomainDatabaseFactory, CustomerProfileRepository customerProfileRepository) {
        this.addressDomainDatabaseFactory = addressDomainDatabaseFactory;
        this.customerProfileRepository = customerProfileRepository;
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
    public CustomerProfileEntity toEntity(CustomerProfile customerProfile) {
        CustomerProfileEntity customerProfileEntity = new CustomerProfileEntity();

        customerProfileEntity.setId(customerProfile.getId());
        customerProfileEntity.setFirstName(customerProfile.getFirstName());
        customerProfileEntity.setLastName(customerProfile.getLastName());
        customerProfileEntity.setPhoneNumber(customerProfile.getPhoneNumber());
        customerProfileEntity.setSecret(customerProfile.getSecret());
        customerProfileEntity.setAddressEntity(addressDomainDatabaseFactory.toEntity(customerProfile.getAddress()));

        return customerProfileEntity;
    }

    @Override
    public CustomerProfile persist(CustomerProfile domainObject) throws DomainValidationException {
        return toDomain(customerProfileRepository.save(toEntity(domainObject)));
    }

    @Override
    public CustomerProfile getDomainById(Long id) throws DomainValidationException {
        return toDomain(customerProfileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(CustomerProfileEntity.class, id)));
    }
}
