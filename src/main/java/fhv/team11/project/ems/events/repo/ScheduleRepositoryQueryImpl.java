package fhv.team11.project.ems.events.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class ScheduleRepositoryQueryImpl implements ScheduleRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Schedule persist(Schedule entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Schedule update(Schedule entity) {
        return null;
    }
}
