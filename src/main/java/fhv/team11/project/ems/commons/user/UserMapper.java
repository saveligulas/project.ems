package fhv.team11.project.ems.commons.user;

import fhv.team11.project.ems.commons.database.IMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements IMapper<UserEntity, User> {

    @Override
    public User toDomainModel(UserEntity userEntity) {
        User user = new User();
        user.setEmail(userEntity.getEmail());
        user.setAuthority(userEntity.getAuthority());
        return user;
    }

    /**
     *
     * @Warning This implementation does not provide the password and Id fields.
     */
    @Override
    public UserEntity toEntity(User model) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(model.getEmail());
        userEntity.setAuthority(model.getAuthority());
        return userEntity;
    }

    @Override
    public UserEntity updateEntity(UserEntity userEntity, User user) {
        if (userEntity == null || user == null) {
            return userEntity;
        }

        userEntity.setEmail(user.getEmail());
        userEntity.setAuthority(user.getAuthority());
        return userEntity;
    }
}
