package fhv.team11.project.ems.booking.mapper;

import fhv.team11.project.ems.booking.repo.BookingEntity;
import fhv.team11.project.ems.booking.transfer.BookingListDTO;
import fhv.team11.project.ems.commons.database.IPresentationDatabaseMapper;
import fhv.team11.project.ems.events.repo.ActiveEvent;
import fhv.team11.project.ems.events.repo.EventDateEntity;

import java.time.LocalDate;
import java.util.Comparator;

public class BookingListDTODatabaseMapper implements IPresentationDatabaseMapper<BookingListDTO, BookingEntity> {
    public static final BookingListDTODatabaseMapper INSTANCE = new BookingListDTODatabaseMapper();

    private BookingListDTODatabaseMapper() {
    }

    @Override
    public BookingListDTO getView(BookingEntity entity) {
        BookingListDTO bookingListDTO = new BookingListDTO();
        bookingListDTO.setId(entity.getId());
        bookingListDTO.setFinancerId(entity.getFinancer().getId());
        bookingListDTO.setEventName(entity.getBookedEvent().getEventTemplate().getName());
        bookingListDTO.setStartDate(findFirstEventDate(entity.getBookedEvent()));
        bookingListDTO.setEventHasMultipleDays(eventHasMultipleDays(entity.getBookedEvent()));
        bookingListDTO.setBookedPlaces(entity.getBookedPlaces());
        bookingListDTO.setStatus(entity.getStatus());
        return bookingListDTO;
    }

    private boolean eventHasMultipleDays(ActiveEvent activeEvent) {
        return activeEvent.getEventDates().size() > 1;
    }

    private LocalDate findFirstEventDate(ActiveEvent activeEvent) {
        return activeEvent.getEventDates().stream()
                .map(EventDateEntity::getDate)
                .min(Comparator.naturalOrder())
                .orElse(null);
    }
}
