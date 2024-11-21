package fhv.team11.project.ems.events.repository;

import fhv.team11.project.ems.events.entity.ActiveEventWizard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveEventWizardRepository extends JpaRepository<ActiveEventWizard, Long> {
    // Additional query methods can be defined here if needed
}
