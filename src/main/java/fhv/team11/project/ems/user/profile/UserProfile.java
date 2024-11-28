package fhv.team11.project.ems.user.profile;

import fhv.team11.project.ems.user.entity.UserEntity;
import fhv.team11.project.ems.user.entity.UserEntityDetails;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "customerProfile")
    private UserEntityDetails userEntity;
}
