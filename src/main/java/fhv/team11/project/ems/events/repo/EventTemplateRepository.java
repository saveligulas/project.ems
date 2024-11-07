package fhv.team11.project.ems.events.repo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTemplateRepository extends JpaRepository<EventTemplate, Long>, EventTemplateRepositoryQuery {

}
