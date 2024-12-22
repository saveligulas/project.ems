package fhv.team11.project.ems.customer;

import fhv.team11.project.ems.commons.domain.ExpandedDomainDatabaseFactory;
import fhv.team11.project.ems.domain.commons.exception.DomainFieldException;
import fhv.team11.project.ems.domain.user.CustomerProfile;
import org.springframework.stereotype.Component;

@Component
public class CustomerProfileDomainDatabaseFactory extends ExpandedDomainDatabaseFactory<CustomerProfile, Long, CustomerProfileEntity> {

    @Override
    public CustomerProfile toDomain(CustomerProfileEntity entity) {
        CustomerProfile customerProfile = null;

        try {
            customerProfile = new CustomerProfile(
                    entity.g
            );
        } catch (DomainFieldException domainFieldException) {

        }

        return customerProfile;
    }

    @Override
    public CustomerProfile getDomainById(Long aLong) {
        return null;
    }

    @Override
    public void persist(CustomerProfile domainObject) {

    }
}
