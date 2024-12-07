package fhv.team11.project.ems.booking.repo;

import fhv.team11.project.ems.commons.database.IRepository;

import java.util.UUID;

public interface BookingIdentifierRepositoryQuery extends IRepository<BookingIdentifier, UUID> {
    void updateUUIDStatus(String uuid, BookingStatus status);
}
