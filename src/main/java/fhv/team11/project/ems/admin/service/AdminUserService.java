package fhv.team11.project.ems.admin.service;

import fhv.team11.project.ems.security.error.RegistrationException;
import fhv.team11.project.ems.security.jwt.JwtTokenService;
import fhv.team11.project.ems.user.entity.UserJDBC;
import fhv.team11.project.ems.user.profile.repo.UserProfilesRepository;
import fhv.team11.project.ems.security.permission.role.Role;
import fhv.team11.project.ems.user.repo.UserJDBCRepository;
import fhv.team11.project.ems.user.transfer.UserView;
import fhv.team11.project.ems.user.transfer.UserListDTO;
import fhv.team11.project.ems.user.transfer.UserListDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdminUserService {

    private final UserJDBCRepository userJDBCRepository;
    private final UserProfilesRepository userProfilesRepository;
    private final JwtTokenService jwtTokenService;

    @Autowired
    public AdminUserService(UserJDBCRepository userJDBCRepository, UserProfilesRepository userProfilesRepository, JwtTokenService jwtTokenService) {
        this.userJDBCRepository = userJDBCRepository;
        this.userProfilesRepository = userProfilesRepository;
        this.jwtTokenService = jwtTokenService;
    }

    public List<UserListDTO> getListOfUsers(int pageNumber, int pageSize) {
        return userJDBCRepository.getUsersForPageNumber(pageNumber, pageSize)
                .stream()
                .map(UserListDTOMapper.INSTANCE::getDTO)
                .toList();
    }

    public UserView getUserDetails(Long id) {
        return new UserView();
    }

    public void createUserWithSetPassword(String email) {
        String setPassword = UUID.fromString(email).toString();

        //TODO: Messaging Bus with email that sends out the set password

        UserJDBC user = new UserJDBC();
        user.setEmail(email);
        user.setPassword(setPassword);
        user.setRoles(List.of(Role.CUSTOMER));

        userJDBCRepository.save(user);
    }

    public void authenticateAsUser(String email) {
        UserJDBC userJDBC = userJDBCRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));



        String token = jwtTokenService.generateAuthenticationToken(userJDBC);
    }
}
