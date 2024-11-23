package fhv.team11.project.ems.booking.repo;

import fhv.team11.project.ems.commons.address.Address;
import fhv.team11.project.ems.commons.address.AddressRepositoryQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class BookingRepositoryQueryImpl implements BookingRepositoryQuery {

    private final BookingRepository bookingRepository;

    public BookingRepositoryQueryImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

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
