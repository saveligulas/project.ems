package fhv.team11.project.ems.booking.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class BookingIdentifierRepositoryQueryImpl implements BookingIdentifierRepositoryQuery{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BookingIdentifier persist(BookingIdentifier entity) {
        return null;
    }

    @Override
    public BookingIdentifier update(BookingIdentifier entity) {
        return null;
    }

    @Transactional
    @Override
    public void updateUUIDStatus(String uuid, BookingStatus status) {
        UUID id = UUID.fromString(uuid);
        entityManager.createQuery("UPDATE BookingIdentifier SET status = :status WHERE id = :id")
                .setParameter("status", status)
                .setParameter("id", id)
                .executeUpdate();
    }
}
