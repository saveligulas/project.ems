package fhv.team11.project.ems.backoffice;

import fhv.team11.project.ems.user.profile.UserProfile;
import jakarta.persistence.Entity;

@Entity
public class BackOfficeProfile extends UserProfile {

    private String employeeNumber;
}
