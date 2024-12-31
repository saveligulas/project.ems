package fhv.team11.project.ems.booking.service;

import fhv.team11.project.ems.booking.repo.*;
import fhv.team11.project.ems.booking.transfer.BookingListDTO;
import fhv.team11.project.ems.booking.transfer.CreateBookingDTO;
import fhv.team11.project.ems.customer.CustomerProfileRepository;
import fhv.team11.project.ems.events.repo.ActiveEventRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Validated
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ActiveEventRepository activeEventRepository;
    private final CustomerProfileRepository customerProfileRepository;
    private final BookingIdentifierRepository bookingIdentifierRepository;


    @Autowired
    public BookingService(BookingRepository bookingRepository, ActiveEventRepository activeEventRepository, CustomerProfileRepository customerProfileRepository, BookingIdentifierRepository bookingIdentifierRepository) {
        this.bookingRepository = bookingRepository;
        this.activeEventRepository = activeEventRepository;
        this.customerProfileRepository = customerProfileRepository;
        this.bookingIdentifierRepository = bookingIdentifierRepository;
    }



    public void createBooking(CreateBookingDTO createBookingDTO, Long eventId) {
        Booking booking = CreateBookingDTOMapper.INSTANCE.getDomain(createBookingDTO);

        // Fetch the ActiveEvent from the repository
        booking.setBookedEvent(
                activeEventRepository.findById(eventId)
                        .orElseThrow(() -> new EntityNotFoundException("ActiveEvent not found"))
        );

        booking.setFinancer(
                customerProfileRepository.findById(createBookingDTO.getFinancerId())
                        .orElseThrow(() -> new EntityNotFoundException("Customer Profile not found"))
        );
        bookingRepository.persist(booking);
    }

    public List<BookingListDTO> getAllBooking() {
        List<Booking> bookings = bookingRepository.findAll();

        return bookings.stream()
                .map(BookingListDTOMapper.INSTANCE::getView)
                .collect(Collectors.toList());
    }

    public void checkInBooking(String identifierId) {
        bookingIdentifierRepository.updateUUIDStatus(identifierId, BookingStatus.Checked_In);
    }
}

