package fhv.team11.project.ems.user.transfer;

import fhv.team11.project.ems.commons.database.IEntityDTOMapper;
import fhv.team11.project.ems.user.entity.UserJDBC;

public class UserListDTOMapper implements IEntityDTOMapper<UserJDBC, UserListDTO> {

    public static final UserListDTOMapper INSTANCE = new UserListDTOMapper();

    @Override
    public UserListDTO getDTO(UserJDBC userJDBC) {
        UserListDTO userListDTO = new UserListDTO();
        userListDTO.setId(userJDBC.getId());
        userListDTO.setUsername(userJDBC.getUsername());
        return userListDTO;
    }
}
