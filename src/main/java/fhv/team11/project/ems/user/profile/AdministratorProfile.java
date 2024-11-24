package fhv.team11.project.ems.user.profile;

import jakarta.persistence.Entity;

@Entity
public class AdministratorProfile extends UserProfile {
    private String token;
}
