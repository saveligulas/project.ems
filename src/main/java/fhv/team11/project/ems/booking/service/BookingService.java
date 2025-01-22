package fhv.team11.project.ems.booking.service;

import fhv.team11.project.ems.booking.BookingDomainDatabaseFactory;
import fhv.team11.project.ems.booking.mapper.BookingListDTODatabaseMapper;
import fhv.team11.project.ems.booking.repo.*;
import fhv.team11.project.ems.booking.transfer.BookingListDTO;
import fhv.team11.project.ems.booking.transfer.CreateBookingDTO;
import fhv.team11.project.ems.commons.error.BackEndError;
import fhv.team11.project.ems.commons.error.EntityNotFoundException;
import fhv.team11.project.ems.commons.validation.domain.IValidationException;
import fhv.team11.project.ems.commons.validation.error.SimpleValidationException;
import fhv.team11.project.ems.customer.CustomerProfileDomainDatabaseFactory;
import fhv.team11.project.ems.customer.CustomerProfileRepository;
import fhv.team11.project.ems.customer.transfer.CustomerProfileDTOMapper;
import fhv.team11.project.ems.domain.booking.Booking;
import fhv.team11.project.ems.domain.booking.CancellationDates;
import fhv.team11.project.ems.domain.booking.Invoice;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.events.Event;
import fhv.team11.project.ems.domain.user.CustomerProfile;
import fhv.team11.project.ems.events.EventDomainDatabaseFactory;
import fhv.team11.project.ems.events.repo.ActiveEventRepository;
import fhv.team11.project.ems.security.jwt.JwtSecurityContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@Validated
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingIdentifierRepository bookingIdentifierRepository;
    private final CustomerProfileDomainDatabaseFactory customerProfileDomainDatabaseFactory;
    private final EventDomainDatabaseFactory eventDomainDatabaseFactory;
    private final BookingDomainDatabaseFactory bookingDomainDatabaseFactory;

    @Autowired
    public BookingService(BookingRepository bookingRepository, ActiveEventRepository activeEventRepository, CustomerProfileRepository customerProfileRepository, BookingIdentifierRepository bookingIdentifierRepository, CustomerProfileDomainDatabaseFactory customerProfileDomainDatabaseFactory, EventDomainDatabaseFactory eventDomainDatabaseFactory, BookingDomainDatabaseFactory bookingDomainDatabaseFactory) {
        this.bookingRepository = bookingRepository;
        this.bookingIdentifierRepository = bookingIdentifierRepository;
        this.customerProfileDomainDatabaseFactory = customerProfileDomainDatabaseFactory;
        this.eventDomainDatabaseFactory = eventDomainDatabaseFactory;
        this.bookingDomainDatabaseFactory = bookingDomainDatabaseFactory;
    }

    public void createBooking(CreateBookingDTO createBookingDTO, Long eventId) throws SimpleValidationException, DomainValidationException {
        CustomerProfile financer;
        Event event;

        try {
            financer = customerProfileDomainDatabaseFactory.getDomainById(createBookingDTO.getFinancer());
        } catch (EntityNotFoundException e) {
            throw new SimpleValidationException("financer", "Customer does not exist");
        }
        try {
            event = eventDomainDatabaseFactory.getDomainById(eventId);
        } catch (EntityNotFoundException e) {
            throw new SimpleValidationException("Event does not exist anymore");
        }

        if (event.getEventTemplate() == null) {
            throw new BackEndError("Active Event without Template exists id: " + event.getId());
        }
        double price = event.getEventTemplate().getPrice() * createBookingDTO.getBookedPlaces();
        //TODO: implement invoices with a factory
        Invoice invoiceDeposit = null;
        UUID uuid = UUID.randomUUID();

        Booking booking = new Booking(
                null,
                financer,
                event,
                createBookingDTO.getBookedPlaces(),
                price,
                BookingStatus.VALID,
                invoiceDeposit,
                null,
                uuid
        );

        bookingDomainDatabaseFactory.persist(booking);
    }

    public List<BookingListDTO> getBookingsBySecurityContext() {
        if (!JwtSecurityContextHolder.hasCustomerProfile()) {
            throw new BackEndError("User has no Customer Profile set");
        }
        return getBookingsByCustomerProfileId(JwtSecurityContextHolder.getUser().getUserEntityDetails().getCustomerProfileEntity().getId());
    }

    public List<BookingListDTO> getBookingsByCustomerProfileId(Long customerProfileId) {
        List<BookingEntity> bookings = bookingRepository.findByFinancerId(customerProfileId);
        return bookings.stream()
               .map(BookingListDTODatabaseMapper.INSTANCE::getView)
               .collect(Collectors.toList());
    }

    public void checkInBooking(String identifierId) {
        bookingIdentifierRepository.updateUUIDStatus(identifierId, BookingStatus.CHECKED_IN);
    }

    public BookingListDTO checkInParticipant(String identifier) throws CheckInException, DomainValidationException {
        UUID uuid = UUID.fromString(identifier);
        Booking booking = bookingDomainDatabaseFactory.toDomain(bookingRepository.findByBookingIdentifierToken(uuid)
                .orElseThrow(() -> new BookingIdentifierNotFoundException(uuid)));
        if (booking.getStatus() == BookingStatus.VALID) {
            booking.setStatus(BookingStatus.CHECKED_IN);
            bookingDomainDatabaseFactory.persist(booking);
            return BookingListDTODatabaseMapper.INSTANCE.getView(bookingRepository.findByBookingIdentifierToken(uuid)
                    .orElseThrow(() -> new BookingIdentifierNotFoundException(uuid)));
        } else {
            throw new CheckInException(List.of("Invalid Booking status"));
        }
    }

    //TODO: change this to invoice identifier that the bank uses
    public void depositPaid(Long id) throws DomainValidationException {
        //TODO: check for correct status else illegal state
        Booking booking = bookingDomainDatabaseFactory.toDomain(bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(BookingEntity.class, id)));
        booking.setStatus(BookingStatus.VALID);
        bookingDomainDatabaseFactory.persist(booking);
    }

    public String getIdentifier(Long bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow(() -> new EntityNotFoundException(BookingEntity.class, bookingId)).getBookingIdentifier().getToken().toString();
    }
}

