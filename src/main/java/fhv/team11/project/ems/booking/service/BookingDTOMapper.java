package fhv.team11.project.ems.booking.service;

import fhv.team11.project.ems.booking.repo.Booking;
import fhv.team11.project.ems.booking.repo.Deposite;
import fhv.team11.project.ems.booking.transfer.BookingDTO;
import fhv.team11.project.ems.booking.transfer.BookingListDTO;
import fhv.team11.project.ems.commons.address.AddressDTOMapper;
import fhv.team11.project.ems.commons.database.IDTOEntityBiMapper;
import fhv.team11.project.ems.commons.database.IDTOEntityMapper;
import fhv.team11.project.ems.events.repo.ActiveEvent;
import fhv.team11.project.ems.events.transfer.ActiveEventListDTO;
import fhv.team11.project.ems.security.jwt.JwtSecurityContextHolder;

public class BookingDTOMapper implements IDTOEntityMapper<Booking,BookingDTO> {

    public static final BookingDTOMapper INSTANCE = new BookingDTOMapper();

    @Override
    public Booking getEntity(BookingDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("BookingDTO must not be null");
        }
        if (dto.getBookedEvent() == null || dto.getBookedEvent().getId() == null) {
            throw new IllegalArgumentException("BookedEvent in BookingDTO must not be null");
        }

        Booking booking = new Booking();
        ActiveEvent activeEvent = new ActiveEvent();
        activeEvent.setId(dto.getBookedEvent().getId());
        booking.setBookedEvent(activeEvent);

        booking.setParticipantAddress(AddressDTOMapper.INSTANCE.toEntity(dto.getParticipantAddress()));
        booking.setFinancer(JwtSecurityContextHolder.getUser());
        booking.setParticipant("TestINMAer");
        //TODO: Set Participant(String name)
        booking.setBookedPlaces(dto.getBookedPlaces());
        booking.setPrice(dto.getPrice());
        booking.setDeposite(Deposite.pending);

        booking.setOptionDate(dto.getOptionDate());
        booking.setCancellationDeadline(dto.getCancellationDeadline());



        return booking;
    }




}
