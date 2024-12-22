package fhv.team11.project.ems.user.domain;

import fhv.team11.project.ems.commons.error.EntityNotFoundException;
import fhv.team11.project.ems.customer.CustomerProfileDomainDatabaseFactory;
import fhv.team11.project.ems.commons.domain.DomainDatabaseFactory;
import fhv.team11.project.ems.domain.commons.exception.DomainFieldException;
import fhv.team11.project.ems.domain.user.CustomerProfile;
import fhv.team11.project.ems.domain.user.User;
import fhv.team11.project.ems.user.entity.UserEntity;
import fhv.team11.project.ems.user.entity.UserEntityRepository;
import fhv.team11.project.ems.user.entity.UserJDBC;
import fhv.team11.project.ems.user.repo.UserJDBCRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDomainDatabaseFactory extends DomainDatabaseFactory<User, Long> {

    private UserJDBCRepository userJDBCRepository;
    private UserEntityRepository userEntityRepository;
    private CustomerProfileDomainDatabaseFactory customerProfileDomainDatabaseFactory;

    @Override
    public User getDomainById(Long id) {
        UserEntity userEntity = userEntityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(UserEntity.class, id));
        UserJDBC userJDBC = userJDBCRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(UserJDBC.class, id));

        User user = null;

        try {
            CustomerProfile customerProfile = customerProfileDomainDatabaseFactory.toDomain(userEntity.getUserEntityDetails().getCustomerProfileEntity());

            //TODO set profiles once they are implemented
            user = new User(
                    userEntity.getId(),
                    userJDBC.getEmail(),
                    userJDBC.getPassword(),
                    userJDBC.getEmail(),
                    userJDBC.getRoles(),
                    List.of(),
                    customerProfile,
                    null,
                    null,
                    null
            );
        } catch (DomainFieldException e) {

        }
        return user;
    }

    @Override
    public void persist(User domainObject) {

    }
}
