package fhv.team11.project.ems.admin.service;

import fhv.team11.project.ems.user.profile.repo.UserProfilesRepository;
import fhv.team11.project.ems.user.repo.UserJDBCRepository;
import fhv.team11.project.ems.user.transfer.UserDTO;
import fhv.team11.project.ems.user.transfer.UserListDTO;
import fhv.team11.project.ems.user.transfer.UserListDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
