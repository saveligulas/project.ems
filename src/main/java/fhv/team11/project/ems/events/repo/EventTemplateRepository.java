package fhv.team11.project.ems.events.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventTemplateRepository extends JpaRepository<EventTemplate, Long>, EventTemplateRepositoryQuery {

}
