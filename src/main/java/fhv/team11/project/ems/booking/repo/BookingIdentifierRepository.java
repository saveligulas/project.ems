package fhv.team11.project.ems.booking.repo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingIdentifierRepository extends JpaRepository<BookingIdentifier, Long>,BookingIdentifierRepositoryQuery {
}
