package fhv.team11.project.ems.booking.repo;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

import java.util.List;

@Repository
public class BookingRepositoryQueryImpl implements BookingRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Booking persist(Booking entity) {
        BookingIdentifier bookingIdentifier = new BookingIdentifier();
        bookingIdentifier.setStatus(BookingStatus.Valid);
        bookingIdentifier.setBooking(entity);

        entityManager.persist(entity.getParticipantAddress());
        entityManager.persist(entity);
        entityManager.persist(bookingIdentifier);
        return entity;
    }

    @Override
    public Booking update(Booking entity) {
        return null;
    }


    public List<Booking> findAll() {
        return entityManager.createQuery("SELECT b FROM Booking b", Booking.class).getResultList();
    }
}
