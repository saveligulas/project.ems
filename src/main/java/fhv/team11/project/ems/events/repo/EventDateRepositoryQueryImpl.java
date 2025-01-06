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
    public EventDateEntity persist(EventDateEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    public EventDateEntity plsPersist(EventDateEntity entity, Schedule schedule, ActiveEvent activeEvent) {
        entityManager.persist(schedule);
        entityManager.persist(activeEvent);
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    @Override
    public EventDateEntity update(EventDateEntity entity) {
        return entityManager.merge(entity);
    }

    public EventDateEntity findById(Long id) {
        return entityManager.find(EventDateEntity.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<EventDateEntity> findAll() {
        return entityManager.createQuery("SELECT e FROM EventDateEntity e", EventDateEntity.class).getResultList();
    }
}
