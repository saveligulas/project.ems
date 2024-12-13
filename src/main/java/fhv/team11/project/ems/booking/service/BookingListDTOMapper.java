package fhv.team11.project.ems.booking.service;

import fhv.team11.project.ems.booking.repo.Booking;
import fhv.team11.project.ems.booking.transfer.BookingIdentifierListDTO;
import fhv.team11.project.ems.booking.transfer.BookingListDTO;
import fhv.team11.project.ems.commons.address.AddressDTOMapper;
import fhv.team11.project.ems.commons.database.IEntityDTOMapper;
import fhv.team11.project.ems.commons.qrcode.QRCodeGenerator;
import fhv.team11.project.ems.events.transfer.ActiveEventListDTO;

public class BookingListDTOMapper implements IEntityDTOMapper<Booking, BookingListDTO> {

    public static final BookingListDTOMapper INSTANCE = new BookingListDTOMapper();

    @Override
    public BookingListDTO getDTO(Booking entity) {
        BookingListDTO dto = new BookingListDTO();

        dto.setId(entity.getId());

        dto.setParticipantAddress(AddressDTOMapper.INSTANCE.toDTO(entity.getFinancer().getAddress()));

        if (entity.getFinancer() != null) {
            dto.setFinancer(null);
        }//TODO implement the reservationist from user

        if (entity.getBookedEvent() != null) {
            ActiveEventListDTO activeEventDTO = new ActiveEventListDTO();
            activeEventDTO.setId(entity.getBookedEvent().getId());
            dto.setBookedEvent(activeEventDTO);
        }

        dto.setDeposit(entity.getDeposit());
        dto.setBookedPlaces(entity.getBookedPlaces());
        dto.setPrice(entity.getPrice());
        dto.setOptionDate(entity.getOptionDate());
        dto.setCancellationDeadline(entity.getCancellationDeadline());

        BookingIdentifierListDTO identifierDTO = new BookingIdentifierListDTO();
        identifierDTO.setToken(entity.getBookingIdentifier().getToken());
        identifierDTO.setStatus(entity.getBookingIdentifier().getStatus());
        identifierDTO.setQRCode(QRCodeGenerator.generateQRCodeImage("localhost8080/bookings/checkin/",entity.getBookingIdentifier().getToken().toString(),100,100));
        dto.setIdentifier(identifierDTO);

        return dto;
    }
}
