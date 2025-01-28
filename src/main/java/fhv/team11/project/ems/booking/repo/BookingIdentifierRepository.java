package fhv.team11.project.ems.booking.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookingIdentifierRepository extends JpaRepository<BookingIdentifier, UUID>,BookingIdentifierRepositoryQuery {
}
