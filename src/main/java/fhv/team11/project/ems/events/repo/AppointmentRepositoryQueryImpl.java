package fhv.team11.project.ems.events.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class AppointmentRepositoryQueryImpl implements AppointmentRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public AppointmentEntity persist(AppointmentEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    @Override
    public AppointmentEntity update(AppointmentEntity entity) {
        return entityManager.merge(entity);
    }
}
