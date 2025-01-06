package fhv.team11.project.ems.backoffice;

import fhv.team11.project.ems.user.profile.UserProfileEntity;
import jakarta.persistence.Entity;

@Entity
public class BackOfficeProfileEntity extends UserProfileEntity {

    private String employeeNumber;
}
