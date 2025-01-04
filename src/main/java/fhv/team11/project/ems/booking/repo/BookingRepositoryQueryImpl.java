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
    //TODO: rework
    public BookingEntity persist(BookingEntity entity) {
        BookingIdentifier bookingIdentifier = new BookingIdentifier();

        bookingIdentifier.setBooking(entity);
        entity.setBookingIdentifier(bookingIdentifier);

        entityManager.persist(entity);

        return entity;
    }

    @Override
    public BookingEntity update(BookingEntity entity) {
        return null;
    }


    public List<BookingEntity> findAll() {
        return entityManager.createQuery("SELECT b FROM BookingEntity b", BookingEntity.class).getResultList();
    }
}
