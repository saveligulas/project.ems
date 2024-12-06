package fhv.team11.project.ems.booking.service;

import fhv.team11.project.ems.booking.repo.Booking;
import fhv.team11.project.ems.booking.transfer.CreateBookingDTO;
import fhv.team11.project.ems.commons.database.IDTOEntityBiMapper;
import fhv.team11.project.ems.commons.database.IDTOEntityMapper;
import fhv.team11.project.ems.events.repo.ActiveEvent;
import fhv.team11.project.ems.events.transfer.ActiveEventListDTO;

public class CreateBookingDTOMapper implements IDTOEntityMapper<Booking, CreateBookingDTO> {

    public static final CreateBookingDTOMapper INSTANCE = new CreateBookingDTOMapper();

    @Override
    public Booking getEntity(CreateBookingDTO dto) {
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
