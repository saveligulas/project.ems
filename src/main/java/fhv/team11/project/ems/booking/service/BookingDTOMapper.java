package fhv.team11.project.ems.booking.service;

import fhv.team11.project.ems.booking.repo.Booking;
import fhv.team11.project.ems.booking.transfer.BookingDTO;
import fhv.team11.project.ems.commons.address.AddressDTOMapper;
import fhv.team11.project.ems.commons.database.IDTOEntityBiMapper;
import fhv.team11.project.ems.events.repo.ActiveEvent;
import fhv.team11.project.ems.security.jwt.JwtSecurityContextHolder;

public class BookingDTOMapper implements IDTOEntityBiMapper<Booking,BookingDTO> {

    public static final BookingDTOMapper INSTANCE = new BookingDTOMapper();

    @Override
    public Booking getEntity(BookingDTO dto) {
        Booking booking = new Booking();
        ActiveEvent activeEvent = new ActiveEvent();
        activeEvent.setId(dto.getBookedEvent().getId());
        booking.setBookedEvent(activeEvent);

        booking.setParticipantAddress(AddressDTOMapper.INSTANCE.toEntity(dto.getParticipantAddress()));

        booking.setReservationist(JwtSecurityContextHolder.getUser());
        booking.setParticipant(JwtSecurityContextHolder.getUser());

        booking.setBookedPlaces(dto.getBookedPlaces());
        booking.setPrice(dto.getPrice());
        booking.setDeposite(dto.getDeposite());

        booking.setOptionDate(dto.getOptionDate());
        booking.setCancellationDeadline(dto.getCancellationDeadline());



        return null;
    }

    @Override
    public BookingDTO getDTO(Booking entity) {
        return null;
    }
}
