package fhv.team11.project.ems.booking.service;

import fhv.team11.project.ems.booking.repo.Booking;
import fhv.team11.project.ems.booking.repo.BookingRepository;
import fhv.team11.project.ems.booking.transfer.BookingDTO;
import fhv.team11.project.ems.commons.address.Address;
import fhv.team11.project.ems.commons.address.AddressDTOMapper;
import fhv.team11.project.ems.commons.address.AddressRepository;
import fhv.team11.project.ems.events.repo.ActiveEventRepository;
import fhv.team11.project.ems.user.repo.UserJDBCRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserJDBCRepository userJDBCRepository;
    private final ActiveEventRepository activeEventRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, UserJDBCRepository userJDBCRepository, ActiveEventRepository activeEventRepository, AddressRepository addressRepository) {
        this.bookingRepository = bookingRepository;
        this.userJDBCRepository = userJDBCRepository;
        this.activeEventRepository = activeEventRepository;
        this.addressRepository = addressRepository;
    }



    public void createBooking(BookingDTO bookingDTO) {
        if (bookingDTO == null || bookingDTO.getBookedEvent() == null || bookingDTO.getBookedEvent().getId() == null) {
            throw new IllegalArgumentException("BookingDTO and bookedEvent.id must not be null");
        }

        Booking booking = BookingDTOMapper.INSTANCE.getEntity(bookingDTO);

        // Fetch the ActiveEvent from the repository
        booking.setBookedEvent(
                activeEventRepository.findById(bookingDTO.getBookedEvent().getId())
                        .orElseThrow(() -> new EntityNotFoundException("ActiveEvent not found"))
        );

        // Handle participant address
        if (booking.getParticipantAddress().getId() != null) {
            booking.setParticipantAddress(
                    addressRepository.findById(booking.getParticipantAddress().getId())
                            .orElseThrow(() -> new EntityNotFoundException("Address not found"))
            );
        } else {
            booking.setParticipantAddress(
                    AddressDTOMapper.INSTANCE.toEntity(bookingDTO.getParticipantAddress())
            );
        }

        bookingRepository.persist(booking);
    }

    public List<BookingDTO> getAllBooking() {
        List<Booking> bookings = bookingRepository.findAll();

        return bookings.stream()
                .map(BookingDTOMapper.INSTANCE::getDTO)
                .collect(Collectors.toList());
    }
}

