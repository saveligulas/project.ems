package fhv.team11.project.ems.user.domain;

import fhv.team11.project.ems.commons.domain.DomainFactory;
import fhv.team11.project.ems.commons.validation.domain.DomainValidatorFactory;
import fhv.team11.project.ems.domain.user.User;
import fhv.team11.project.ems.user.entity.UserEntity;
import fhv.team11.project.ems.user.transfer.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDomainFactory extends DomainFactory<UserEntity, User, UserView> {

    @Autowired
    public UserDomainFactory(DomainValidatorFactory domainValidatorFactory) {
        super(domainValidatorFactory);
    }

    @Override
    public User getDomain() {
        return null;
    }

    @Override
    public User getDomainFromEntity(UserEntity entity) {
        return null;
    }

    @Override
    public User getDomainFromModel(UserView model) {
        return null;
    }
}
