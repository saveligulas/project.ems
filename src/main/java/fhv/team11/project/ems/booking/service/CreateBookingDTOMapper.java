package fhv.team11.project.ems.booking.service;

import fhv.team11.project.ems.booking.repo.Booking;
import fhv.team11.project.ems.booking.transfer.CreateBookingDTO;
import fhv.team11.project.ems.commons.mapper.IPresentationDomainMapper;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.events.repo.ActiveEvent;

public class CreateBookingDTOMapper implements IPresentationDomainMapper<CreateBookingDTO, Booking> {

    public static final CreateBookingDTOMapper INSTANCE = new CreateBookingDTOMapper();

    @Override
    public Booking getDomain(CreateBookingDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("BookingDTO must not be null");
        }

        Booking booking = new Booking();
        ActiveEvent activeEvent = new ActiveEvent();

        //ALERT: Shallow copy of activeEvent
        booking.setBookedEvent(activeEvent);
        booking.setBookedPlaces(dto.getBookedPlaces());

        return booking;
    }
}
