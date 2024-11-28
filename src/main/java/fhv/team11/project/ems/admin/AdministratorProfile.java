package fhv.team11.project.ems.admin;

import fhv.team11.project.ems.user.profile.UserProfile;
import jakarta.persistence.Entity;

@Entity
public class AdministratorProfile extends UserProfile {
    private String token;
}
