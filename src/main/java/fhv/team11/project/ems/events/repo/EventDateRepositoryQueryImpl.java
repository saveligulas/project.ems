package fhv.team11.project.ems.events.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

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

    @Transactional
    @Override
    public EventDate update(EventDate entity) {
        return entityManager.merge(entity);
    }

    public EventDate findById(Long id) {
        return entityManager.find(EventDate.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<EventDate> findAll() {
        return entityManager.createQuery("SELECT e FROM EventDate e", EventDate.class).getResultList();
    }
}
