package fhv.team11.project.ems.booking.repo;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

@Repository
public class BookingRepositoryQueryImpl implements BookingRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Booking persist(Booking entity) {
        entityManager.persist(entity.getParticipantAddress());
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Booking update(Booking entity) {
        return null;
    }
}
