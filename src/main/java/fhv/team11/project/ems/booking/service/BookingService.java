package fhv.team11.project.ems.booking.service;

import fhv.team11.project.ems.booking.repo.Booking;
import fhv.team11.project.ems.booking.repo.BookingRepository;
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


    @Autowired
    public BookingService(BookingRepository bookingRepository, ActiveEventRepository activeEventRepository, CustomerProfileRepository customerProfileRepository) {
        this.bookingRepository = bookingRepository;
        this.activeEventRepository = activeEventRepository;
        this.customerProfileRepository = customerProfileRepository;
    }



    public void createBooking(CreateBookingDTO createBookingDTO, Long eventId) {
        Booking booking = CreateBookingDTOMapper.INSTANCE.getEntity(createBookingDTO);

        // Fetch the ActiveEvent from the repository
        booking.setBookedEvent(
                activeEventRepository.findById(eventId)
                        .orElseThrow(() -> new EntityNotFoundException("ActiveEvent not found"))
        );

        booking.setFinancer(
                customerProfileRepository.findById(createBookingDTO.getFinancerId())
                        .orElseThrow(() -> new EntityNotFoundException("Customer Profile not found"))
        );

        bookingRepository.save(booking);
    }
}

