package fhv.team11.project.ems.user.profile.repo;

import fhv.team11.project.ems.user.profile.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Long> {
}
