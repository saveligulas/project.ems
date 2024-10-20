package fhv.team11.project.ems.commons.user;

import lombok.Data;

@Data
public class User {
    private String email;
    private Authority authority;
}
