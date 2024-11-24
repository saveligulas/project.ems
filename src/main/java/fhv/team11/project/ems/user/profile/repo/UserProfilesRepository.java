package fhv.team11.project.ems.user.profile.repo;

import fhv.team11.project.ems.user.transfer.CustomerProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserProfilesRepository {

    private final CustomerProfileRepository customerProfileRepository;

    @Autowired
    public UserProfilesRepository(CustomerProfileRepository customerProfileRepository) {
        this.customerProfileRepository = customerProfileRepository;
    }
}
