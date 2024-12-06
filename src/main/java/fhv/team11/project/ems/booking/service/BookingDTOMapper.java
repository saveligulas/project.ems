package fhv.team11.project.ems.booking.service;

import fhv.team11.project.ems.booking.repo.Booking;
import fhv.team11.project.ems.booking.transfer.CreateBookingDTO;
import fhv.team11.project.ems.commons.database.IDTOEntityBiMapper;
import fhv.team11.project.ems.customer.transfer.CustomerProfileDTOMapper;
import fhv.team11.project.ems.events.repo.ActiveEvent;
import fhv.team11.project.ems.events.transfer.ActiveEventListDTO;

public class BookingDTOMapper implements IDTOEntityBiMapper<Booking, CreateBookingDTO> {

    public static final BookingDTOMapper INSTANCE = new BookingDTOMapper();

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


    @Override
    public CreateBookingDTO getDTO(Booking entity) {
        CreateBookingDTO dto = new CreateBookingDTO();

        if (entity.getBookedEvent() != null) {
            ActiveEventListDTO activeEventDTO = new ActiveEventListDTO();
            activeEventDTO.setId(entity.getBookedEvent().getId());
        }

        dto.setBookedPlaces(entity.getBookedPlaces());

        return dto;
    }

}
