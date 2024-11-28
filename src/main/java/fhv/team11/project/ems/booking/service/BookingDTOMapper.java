package fhv.team11.project.ems.booking.service;

import fhv.team11.project.ems.booking.repo.Booking;
import fhv.team11.project.ems.booking.transfer.BookingDTO;
import fhv.team11.project.ems.commons.address.AddressDTOMapper;
import fhv.team11.project.ems.commons.database.IDTOEntityBiMapper;
import fhv.team11.project.ems.events.repo.ActiveEvent;
import fhv.team11.project.ems.events.transfer.ActiveEventListDTO;
import fhv.team11.project.ems.security.jwt.JwtSecurityContextHolder;

public class BookingDTOMapper implements IDTOEntityBiMapper<Booking,BookingDTO> {

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
        booking.setDeposite(dto.getDeposite());

        booking.setOptionDate(dto.getOptionDate());
        booking.setCancellationDeadline(dto.getCancellationDeadline());



        return booking;
    }


    @Override
    public BookingDTO getDTO(Booking entity) {
        BookingDTO dto = new BookingDTO();

        dto.setId(entity.getId());

        dto.setParticipantAddress(AddressDTOMapper.INSTANCE.toDTO(entity.getParticipantAddress()));

        if (entity.getParticipant() != null) {
            dto.setParticipant(null);
        }//TODO user

        if (entity.getFinancer() != null) {
            dto.setReservationist(null);
        }//TODO implement the reservationist from user

        if (entity.getBookedEvent() != null) {
            ActiveEventListDTO activeEventDTO = new ActiveEventListDTO();
            activeEventDTO.setId(entity.getBookedEvent().getId());
            dto.setBookedEvent(activeEventDTO);
        }

        dto.setDeposite(entity.getDeposite());
        dto.setBookedPlaces(entity.getBookedPlaces());
        dto.setPrice(entity.getPrice());
        dto.setOptionDate(entity.getOptionDate());
        dto.setCancellationDeadline(entity.getCancellationDeadline());

        return dto;
    }

}
