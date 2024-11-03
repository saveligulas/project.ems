package fhv.team11.project.ems.events.repo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlueprintRepository extends JpaRepository<Blueprint, Long>, BlueprintRepositoryQuery {
}
