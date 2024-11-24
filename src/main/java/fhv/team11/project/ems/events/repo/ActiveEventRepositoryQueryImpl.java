package fhv.team11.project.ems.events.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActiveEventRepositoryQueryImpl implements ActiveEventRepositoryQuery {

    private final EventTemplateRepository eventTemplateRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public ActiveEventRepositoryQueryImpl(EventTemplateRepository eventTemplateRepository) {
        this.eventTemplateRepository = eventTemplateRepository;
    }

    @Transactional
    @Override
    public ActiveEvent persist(ActiveEvent entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    @Override
    public ActiveEvent update(ActiveEvent entity) {
        return entityManager.merge(entity);
    }

    public ActiveEvent findById(Long id) {
        return entityManager.find(ActiveEvent.class, id);
    }
    @SuppressWarnings("unchecked")
    public List<ActiveEvent> findAll() {
        return entityManager.createQuery("SELECT a FROM ActiveEvent a").getResultList();
    }
    public List<ActiveEvent> findAllWithTemplatesAndDates() {
        /*return entityManager.createQuery(
                "SELECT a FROM ActiveEvent a " +
                        "JOIN FETCH a.eventTemplate t " +
                        "JOIN FETCH a.eventDate d", ActiveEvent.class
        ).getResultList();*/

        return entityManager.createQuery(
                        "SELECT DISTINCT ae FROM ActiveEvent ae " +
                                "JOIN FETCH ae.eventDate ed " +
                                "JOIN FETCH ae.eventTemplate et " +
                                "JOIN FETCH ed.schedule s " +
                                "JOIN FETCH s.appointments a " +
                                "LEFT JOIN FETCH ae.bookings b", ActiveEvent.class)
                .getResultList();
    }
}
