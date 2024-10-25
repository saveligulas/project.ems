package fhv.team11.project.ems.commons.user;

import fhv.team11.project.ems.commons.error.EntityNotFoundException;
import fhv.team11.project.ems.commons.database.IDatabaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDatabaseService implements IDatabaseMapper<UserEntity, Long> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDatabaseService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserEntity> findByEmail(String email) {
        Optional<UserEntity> userEntity;
        try {
            userEntity = Optional.of(userRepository.findByEmail(email));
        } catch (UsernameNotFoundException e) {
            userEntity = Optional.empty();
        }
        return userEntity;
    }


    @Override
    public UserEntity save(UserEntity entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        userRepository.save(entity);
        return userRepository.findByEmail(entity.getEmail());
    }

    @Override
    public Optional<UserEntity> findById(Long aLong) throws EntityNotFoundException {
        return null;
    }

    @Override
    public List<UserEntity> findAll() {
        return null;
    }

    @Override
    public UserEntity update(UserEntity entity) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
