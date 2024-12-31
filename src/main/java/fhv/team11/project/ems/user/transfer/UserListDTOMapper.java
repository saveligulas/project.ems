package fhv.team11.project.ems.user.transfer;

import fhv.team11.project.ems.commons.mapper.IDomainPresentationMapper;
import fhv.team11.project.ems.user.entity.UserJDBC;

public class UserListDTOMapper {

    public static final UserListDTOMapper INSTANCE = new UserListDTOMapper();

    public UserListDTO getView(UserJDBC userJDBC) {
        UserListDTO userListDTO = new UserListDTO();
        userListDTO.setId(userJDBC.getId());
        userListDTO.setUsername(userJDBC.getUsername());
        return userListDTO;
    }
}
