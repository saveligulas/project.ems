package fhv.team11.project.ems.commons.user.repo;

import fhv.team11.project.ems.commons.error.EntityNotFoundException;
import fhv.team11.project.ems.commons.database.IDatabaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserJDBCRepository implements IDatabaseMapper<UserJDBC, Long> {

    private final UserRepositoryQuery userRepositoryQuery;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserJDBCRepository(UserRepositoryQuery userRepositoryQuery, PasswordEncoder passwordEncoder) {
        this.userRepositoryQuery = userRepositoryQuery;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserJDBC> findByEmail(String email) {
        Optional<UserJDBC> userEntity;
        try {
            userEntity = Optional.of(userRepositoryQuery.findByEmail(email));
        } catch (UsernameNotFoundException e) {
            userEntity = Optional.empty();
        }
        return userEntity;
    }


    @Override
    public UserJDBC save(UserJDBC entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        userRepositoryQuery.save(entity);
        return userRepositoryQuery.findByEmail(entity.getEmail());
    }

    @Override
    public Optional<UserJDBC> findById(Long aLong) throws EntityNotFoundException {
        return null;
    }

    @Override
    public List<UserJDBC> findAll() {
        return null;
    }

    @Override
    public UserJDBC update(UserJDBC entity) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
