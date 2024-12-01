package fhv.team11.project.ems.user.transfer;

import fhv.team11.project.ems.commons.validation.model.IModelAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListDTO implements IModelAttribute {
    private Long id;
    private String username;
}
