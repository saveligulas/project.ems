package fhv.team11.project.ems.organization.repo;

import fhv.team11.project.ems.organization.event.EventOrganization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventOrganizationRepository extends JpaRepository<EventOrganization, Long> {
}
