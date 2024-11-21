package fhv.team11.project.ems.events.repo;

import fhv.team11.project.ems.commons.database.IRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDateRepository extends JpaRepository<EventDate,Long>,EventDateRepositoryQuery{
}
