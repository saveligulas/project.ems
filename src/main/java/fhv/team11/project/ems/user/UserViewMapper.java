package fhv.team11.project.ems.user;

import org.springframework.stereotype.Component;

@Component
public class UserViewMapper {

    public static UserViewModel toViewModel(UserModel userModel) {
        UserViewModel userViewModel = new UserViewModel();
        userViewModel.setEmail(userModel.getEmail());
        userViewModel.setAuthority(userModel.getAuthority());
        return userViewModel;
    }

    /**
     *
     * @param userViewModel
     * @return returns the user model corresponding to the ViewModel. But the password and Id fields will be missing
     */
    public static UserModel toModel(UserViewModel userViewModel) {
        UserModel userModel = new UserModel();
        userModel.setEmail(userViewModel.getEmail());
        userModel.setAuthority(userViewModel.getAuthority());
        return userModel;
    }
}
