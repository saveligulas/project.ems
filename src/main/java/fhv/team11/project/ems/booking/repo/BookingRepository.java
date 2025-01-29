package fhv.team11.project.ems.booking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long>, BookingRepositoryQuery {
    List<BookingEntity> findByFinancerId(Long financerId);

    @Query("SELECT b FROM BookingEntity b JOIN b.bookingIdentifier bi WHERE bi.token = :token")
    Optional<BookingEntity> findByBookingIdentifierToken(@Param("token") UUID token);

    @Query("SELECT b FROM BookingEntity b WHERE b.bookedEvent.id = :eventId")
    List<BookingEntity> findBookingsByEventId(@Param("eventId") Long eventId);

    @Query("SELECT DISTINCT b FROM BookingEntity b " +
            "LEFT JOIN FETCH b.deposit d " +
            "LEFT JOIN FETCH b.booking bk " +
            "LEFT JOIN FETCH b.financer " +
            "LEFT JOIN FETCH b.bookedEvent " +
            "WHERE d.identifier = :invoiceIdentifier " +
            "OR bk.identifier = :invoiceIdentifier")
    Optional<BookingEntity> findByInvoiceIdentifierWithFetch(@Param("invoiceIdentifier") UUID invoiceIdentifier);
}
