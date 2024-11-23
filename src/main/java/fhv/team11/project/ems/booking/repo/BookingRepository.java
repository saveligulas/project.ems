package fhv.team11.project.ems.booking.repo;

import fhv.team11.project.ems.events.repo.ActiveEventRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>, ActiveEventRepositoryQuery {
}
