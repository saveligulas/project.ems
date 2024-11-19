package fhv.team11.project.ems.events.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class AppointmentRepositoryQueryImpl implements AppointmentRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Appointment persist(Appointment entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Appointment update(Appointment entity) {
        return null;
    }
}
