package fhv.team11.project.ems.events.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActiveEventRepository extends JpaRepository<ActiveEvent, Long>, ActiveEventRepositoryQuery {
    @Query("SELECT a FROM ActiveEvent a JOIN FETCH a.eventTemplate t JOIN FETCH a.eventDate d")
    List<ActiveEvent> findAllWithTemplatesAndDates();
}
