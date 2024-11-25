package fhv.team11.project.ems.admin.service;

import fhv.team11.project.ems.security.error.RegistrationEmailAlreadyRegisteredException;
import fhv.team11.project.ems.security.jwt.AuthenticationService;
import fhv.team11.project.ems.security.transfer.RegisterRequest;
import fhv.team11.project.ems.user.entity.UserJDBC;
import fhv.team11.project.ems.user.profile.repo.UserProfilesRepository;
import fhv.team11.project.ems.user.repo.Role;
import fhv.team11.project.ems.user.repo.UserJDBCRepository;
import fhv.team11.project.ems.user.transfer.UserDTO;
import fhv.team11.project.ems.user.transfer.UserListDTO;
import fhv.team11.project.ems.user.transfer.UserListDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdminUserService {

    private final UserJDBCRepository userJDBCRepository;
    private final UserProfilesRepository userProfilesRepository;

    @Autowired
    public AdminUserService(UserJDBCRepository userJDBCRepository, UserProfilesRepository userProfilesRepository) {
        this.userJDBCRepository = userJDBCRepository;
        this.userProfilesRepository = userProfilesRepository;
    }

    public List<UserListDTO> getListOfUsers(int pageNumber, int pageSize) {
        return userJDBCRepository.getUsersForPageNumber(pageNumber, pageSize)
                .stream()
                .map(UserListDTOMapper.INSTANCE::getDTO)
                .toList();
    }

    public UserDTO getUserDetails(Long id) {
        return new UserDTO();
    }

    public void createUserWithSetPassword(String email) {
        String setPassword = UUID.fromString(email).toString();

        //TODO: Messaging Bus with email that sends out the set password

        if (userJDBCRepository.findByEmail(email).isPresent()) {
            throw new RegistrationEmailAlreadyRegisteredException();
        }

        UserJDBC user = new UserJDBC();
        user.setEmail(email);
        user.setPassword(setPassword);
        user.setRoles(List.of(Role.CUSTOMER));

        userJDBCRepository.save(user);
    }
}
