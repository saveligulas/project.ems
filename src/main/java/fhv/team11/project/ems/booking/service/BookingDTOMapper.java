package fhv.team11.project.ems.booking.service;

import fhv.team11.project.ems.booking.repo.Booking;
import fhv.team11.project.ems.booking.transfer.BookingDTO;
import fhv.team11.project.ems.commons.database.IDTOEntityBiMapper;

public class BookingDTOMapper implements IDTOEntityBiMapper<Booking,BookingDTO> {

    private static final BookingDTOMapper INSTANCE = new BookingDTOMapper();

    @Override
    public Booking getEntity(BookingDTO dto) {
        return null;
    }

    @Override
    public BookingDTO getDTO(Booking entity) {
        return null;
    }
}
