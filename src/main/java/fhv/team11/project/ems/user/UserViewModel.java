package fhv.team11.project.ems.user;

import fhv.team11.project.ems.security.json.AuthenticationResponse;
import lombok.Data;

@Data
public class UserViewModel {
    private String email;
    private Authority authority;
}
