package fhv.team11.project.ems.events.repo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTemplateEntityRepository extends JpaRepository<EventTemplateEntity, Long>, EventTemplateEntityRepositoryQuery {

}
