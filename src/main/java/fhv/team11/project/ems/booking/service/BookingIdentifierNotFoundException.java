package fhv.team11.project.ems.booking.service;

import fhv.team11.project.ems.booking.repo.BookingEntity;
import fhv.team11.project.ems.commons.error.EntityNotFoundException;

import java.util.UUID;

public class BookingIdentifierNotFoundException extends EntityNotFoundException {
    public BookingIdentifierNotFoundException(UUID id) {
        super(BookingEntity.class, id);
    }
}
