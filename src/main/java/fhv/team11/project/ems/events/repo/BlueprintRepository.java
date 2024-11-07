package fhv.team11.project.ems.events.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlueprintRepository extends JpaRepository<Blueprint, Long>, BlueprintRepositoryQuery {

}
