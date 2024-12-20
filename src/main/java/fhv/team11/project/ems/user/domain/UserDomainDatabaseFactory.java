package fhv.team11.project.ems.user.domain;

import fhv.team11.project.ems.domain.commons.DomainDatabaseFactory;
import fhv.team11.project.ems.commons.validation.domain.DomainValidatorFactory;
import fhv.team11.project.ems.domain.user.User;
import fhv.team11.project.ems.user.entity.UserEntity;
import fhv.team11.project.ems.user.transfer.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDomainDatabaseFactory extends DomainDatabaseFactory<User, Long> {

    @Override
    public User toDomain(Long id) {
        return null;
    }

    @Override
    public void persist(User domainObject) {

    }
}
