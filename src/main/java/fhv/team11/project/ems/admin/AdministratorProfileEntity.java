package fhv.team11.project.ems.admin;

import fhv.team11.project.ems.user.profile.UserProfileEntity;
import jakarta.persistence.Entity;

@Entity
public class AdministratorProfileEntity extends UserProfileEntity {
    private String token;
}
