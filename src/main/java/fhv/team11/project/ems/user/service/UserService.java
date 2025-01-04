package fhv.team11.project.ems.user.service;

import fhv.team11.project.ems.commons.validation.domain.DomainValidatorFactory;
import fhv.team11.project.ems.security.transfer.RegisterRequest;
import fhv.team11.project.ems.user.entity.UserEntityRepository;
import fhv.team11.project.ems.user.repo.UserJDBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserJDBCRepository userJDBCRepository;
    private final UserEntityRepository userEntityRepository;

    @Autowired
    public UserService(UserJDBCRepository userJDBCRepository, UserEntityRepository userEntityRepository) {
        this.userJDBCRepository = userJDBCRepository;
        this.userEntityRepository = userEntityRepository;
    }

    public void save(String email, String password) {
        this.save(new RegisterRequest(email, password, password));
    }

    public void save(RegisterRequest registerRequest) {

    }
}
