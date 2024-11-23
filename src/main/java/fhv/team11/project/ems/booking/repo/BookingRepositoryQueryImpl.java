package fhv.team11.project.ems.booking.repo;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class BookingRepositoryQueryImpl implements BookingRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Booking persist(Booking entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Booking update(Booking entity) {
        return null;
    }
}
