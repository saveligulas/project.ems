package fhv.team11.project.ems.events.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

public class ScheduleRepositoryQueryImpl implements ScheduleRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Schedule persist(Schedule entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    @Override
    public Schedule update(Schedule entity) {
        return entityManager.merge(entity);
    }

    public Schedule findById(Long id) {
        return entityManager.find(Schedule.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Schedule> findAll() {
        return entityManager.createQuery("SELECT s FROM Schedule s", Schedule.class).getResultList();
    }
}
