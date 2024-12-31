package fhv.team11.project.ems.user.domain;

import fhv.team11.project.ems.commons.domain.IFindByIdDomainDatabaseMapper;
import fhv.team11.project.ems.commons.domain.IHandleDomainPersistence;
import fhv.team11.project.ems.commons.error.EntityNotFoundException;
import fhv.team11.project.ems.customer.CustomerProfileDomainDatabaseFactory;
import fhv.team11.project.ems.commons.domain.DomainDatabaseFactory;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.user.CustomerProfile;
import fhv.team11.project.ems.domain.user.User;
import fhv.team11.project.ems.user.entity.UserEntity;
import fhv.team11.project.ems.user.entity.UserEntityDetails;
import fhv.team11.project.ems.user.entity.UserEntityRepository;
import fhv.team11.project.ems.user.entity.UserJDBC;
import fhv.team11.project.ems.user.repo.UserJDBCRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDomainDatabaseFactory extends DomainDatabaseFactory implements IFindByIdDomainDatabaseMapper<User, Long>, IHandleDomainPersistence<User> {

    private final UserJDBCRepository userJDBCRepository;
    private final UserEntityRepository userEntityRepository;
    private final CustomerProfileDomainDatabaseFactory customerProfileDomainDatabaseFactory;

    @Autowired
    public UserDomainDatabaseFactory(UserJDBCRepository userJDBCRepository, UserEntityRepository userEntityRepository, CustomerProfileDomainDatabaseFactory customerProfileDomainDatabaseFactory) {
        this.userJDBCRepository = userJDBCRepository;
        this.userEntityRepository = userEntityRepository;
        this.customerProfileDomainDatabaseFactory = customerProfileDomainDatabaseFactory;
    }

    @Override
    public User getDomainById(Long id) throws DomainValidationException {
        UserEntity userEntity = userEntityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(UserEntity.class, id));
        UserJDBC userJDBC = userJDBCRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(UserJDBC.class, id));

        CustomerProfile customerProfile = customerProfileDomainDatabaseFactory.toDomain(userEntity.getUserEntityDetails().getCustomerProfileEntity());

        //TODO set profiles once they are implemented
        return new User(
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
    }

    @Override
    @Transactional
    public User persist(User user) throws DomainValidationException {
        UserJDBC userJDBC = new UserJDBC();

        userJDBC.setEmail(user.getEmail());
        userJDBC.setPassword(user.getPassword());
        userJDBC.setRoles(user.getRoles());

        userJDBC = userJDBCRepository.save(userJDBC);

        UserEntity userEntity = new UserEntity();
        UserEntityDetails userEntityDetails = new UserEntityDetails();

        userEntity.setId(userJDBC.getId());
        //TODO: add this for all profiles
        userEntityDetails.setUser(userEntity);
        if (user.getCustomerProfile() != null) {
            userEntityDetails.setCustomerProfileEntity(customerProfileDomainDatabaseFactory.toNotPersistedEntity(user.getCustomerProfile()));
        }

        userEntity.setUserEntityDetails(userEntityDetails);
        userEntity = userEntityRepository.save(userEntity);

        return toDomain(userJDBC, userEntity);
    }

    private User toDomain(UserJDBC userJDBC, UserEntity userEntity) throws DomainValidationException {
        return new User(
                userEntity.getId(),
                userJDBC.getEmail(),
                userJDBC.getPassword(),
                userJDBC.getEmail(),
                userJDBC.getRoles(),
                List.of(),
                customerProfileDomainDatabaseFactory.toDomain(userEntity.getUserEntityDetails().getCustomerProfileEntity()),
                null,
                null,
                null
        );
    }
}
