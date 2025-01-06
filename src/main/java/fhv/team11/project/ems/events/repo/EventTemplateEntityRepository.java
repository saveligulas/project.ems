package fhv.team11.project.ems.events.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface EventTemplateEntityRepository extends JpaRepository<EventTemplateEntity, Long>, EventTemplateEntityRepositoryQuery, JpaSpecificationExecutor<EventTemplateEntity> {
    @Query("SELECT DISTINCT et FROM EventTemplateEntity et JOIN et.activeEvents ae")
    Page<EventTemplateEntity> findAllWithActiveEvents(Pageable pageable);
}
