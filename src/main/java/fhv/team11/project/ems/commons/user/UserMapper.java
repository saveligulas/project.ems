package fhv.team11.project.ems.commons.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static User toViewModel(UserEntity userModel) {
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setAuthority(userModel.getAuthority());
        return user;
    }

    /**
     *
     * @param user
     * @return returns the user model corresponding to the ViewModel. But the password and Id fields will be missing
     */
    public static UserEntity toModel(User user) {
        UserEntity userModel = new UserEntity();
        userModel.setEmail(user.getEmail());
        userModel.setAuthority(user.getAuthority());
        return userModel;
    }
}
