package fhv.team11.project.ems.customer;

import fhv.team11.project.ems.commons.address.Address;
import fhv.team11.project.ems.commons.domain.DomainDatabaseFactory;
import fhv.team11.project.ems.commons.domain.ISimpleDomainDatabaseMapper;
import fhv.team11.project.ems.commons.validation.domain.handler.HandleBindingResultException;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.user.CustomerProfile;
import fhv.team11.project.ems.domain.user.TempAddress;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CustomerProfileDomainDatabaseFactory extends DomainDatabaseFactory<CustomerProfile, Long> implements HandleBindingResultException, ISimpleDomainDatabaseMapper<CustomerProfile, CustomerProfileEntity> {

    @Override
    public CustomerProfile toDomain(@Nullable CustomerProfileEntity entity) {
        if (entity == null) {
            return null;
        }

        CustomerProfile customerProfile = null;

        try {
            customerProfile = new CustomerProfile(
                    entity.getId(),
                    entity.getSecret(),
                    entity.getPhoneNumber(),
                    //TODO add real address
                    //entity.getAddress(),
                    new TempAddress(),
                    entity.getFirstName(),
                    entity.getLastName());
        } catch (DomainValidationException e) {

        }

        return customerProfile;
    }

    @Override
    public CustomerProfileEntity toNotPersistedEntity(CustomerProfile customerProfile) {
        CustomerProfileEntity customerProfileEntity = new CustomerProfileEntity();

        customerProfileEntity.setFirstName(customerProfile.getFirstName());
        customerProfileEntity.setLastName(customerProfile.getLastName());
        customerProfileEntity.setPhoneNumber(customerProfile.getPhoneNumber());
        customerProfileEntity.setSecret(customerProfile.getSecret());
        //TODO: put real address
        customerProfileEntity.setAddress(new Address());

        return customerProfileEntity;
    }

    @Override
    public CustomerProfile getDomainById(Long id) {
        return null;
    }

    @Override
    public Long persist(CustomerProfile domainObject) {
        return null;
    }
}
