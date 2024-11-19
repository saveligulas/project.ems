package fhv.team11.project.ems.events.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class EventDateRepositoryQueryImpl implements EventDateRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public EventDate persist(EventDate entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    public EventDate plsPersist(EventDate entity, Schedule schedule, ActiveEvent activeEvent) {
        entityManager.persist(schedule);
        entityManager.persist(activeEvent);
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public EventDate update(EventDate entity) {
        return null;
    }
}
