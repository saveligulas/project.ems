package fhv.team11.project.ems.events.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveEventRepository extends JpaRepository<ActiveEvent, Long>, ActiveEventRepositoryQuery {
}
