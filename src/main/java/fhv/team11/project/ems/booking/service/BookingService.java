package fhv.team11.project.ems.booking.service;

import fhv.team11.project.ems.booking.BookingDomainDatabaseFactory;
import fhv.team11.project.ems.booking.mapper.BookingListDTODatabaseMapper;
import fhv.team11.project.ems.booking.repo.*;
import fhv.team11.project.ems.booking.transfer.BookingListDTO;
import fhv.team11.project.ems.booking.transfer.CreateBookingDTO;
import fhv.team11.project.ems.commons.error.BackEndError;
import fhv.team11.project.ems.commons.error.EntityNotFoundException;
import fhv.team11.project.ems.commons.validation.error.SimpleValidationException;
import fhv.team11.project.ems.customer.CustomerProfileDomainDatabaseFactory;
import fhv.team11.project.ems.customer.CustomerProfileRepository;
import fhv.team11.project.ems.domain.booking.Booking;
import fhv.team11.project.ems.domain.booking.Invoice;
import fhv.team11.project.ems.domain.commons.exception.DomainInstantiationException;
import fhv.team11.project.ems.domain.commons.exception.DomainStateException;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.commons.interfaces.ILineItem;
import fhv.team11.project.ems.domain.commons.interfaces.SimpleLineItem;
import fhv.team11.project.ems.domain.events.Event;
import fhv.team11.project.ems.domain.events.EventDate;
import fhv.team11.project.ems.domain.user.CustomerProfile;
import fhv.team11.project.ems.events.EventDomainDatabaseFactory;
import fhv.team11.project.ems.events.repo.ActiveEventRepository;
import fhv.team11.project.ems.security.jwt.JwtSecurityContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
            financer = customerProfileDomainDatabaseFactory.getDomainById(createBookingDTO.getFinancerId());
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
        double price = event.getEventTemplate().getPrice() * createBookingDTO.getBookedEvent();
        double priceDeposit = price * 0.1;

        UUID uuid = UUID.randomUUID();
        Booking booking = new Booking(
                null,
                financer,
                event,
                createBookingDTO.getBookedEvent(),
                price,
                BookingStatus.DEPOSIT_UNPAID,
                null,
                null,
                uuid
        );

        SimpleLineItem depositLineItem = new SimpleLineItem(
                BigDecimal.valueOf(priceDeposit),
                booking.getId(),
                "Deposit for: " + booking.getName(),
                booking.getDescription(),
                booking.getCount(),
                booking.getSubLineItems()
        );

        Invoice invoiceDeposit = new Invoice(
                financer,
                null,
                event.getFirstEventDate(),
                LocalDate.now(),
                event.getFirstEventDate().minusDays(1),
                UUID.randomUUID(),
                List.of(depositLineItem),
                createBookingDTO.getPaymentOption().getPaymentMethod(),
                createBookingDTO.getPaymentOption().getInvoiceDelivery(),
                financer,
                event,
                null

        );

        booking.setInvoiceDeposit(invoiceDeposit);

        if (event.getPlacesLeft() < createBookingDTO.getBookedEvent()) {
            throw new SimpleValidationException("bookedPlaces", "Event has not enough places left");
        }

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

    public BookingListDTO checkInParticipant(String identifier) throws CheckInException, DomainValidationException {
        UUID uuid = UUID.fromString(identifier);
        Booking booking = bookingDomainDatabaseFactory.toDomain(bookingRepository.findByBookingIdentifierToken(uuid)
                .orElseThrow(() -> new BookingIdentifierNotFoundException(uuid)));

        Event event = booking.getBookedEvent();
        EventDate eventDateToday = event.getEventDateForDate(LocalDate.now());

        if (eventDateToday == null) {
            throw new CheckInException(List.of("Event does not take place today."));
        }

        if (booking.getStatus() == BookingStatus.RESOLVED) {
            throw new CheckInException(List.of("Participant has already been resolved.-"));
        }

        if (booking.getStatus() == BookingStatus.CHECKED_OUT) {
            throw new CheckInException(List.of("Participant is already checked out.-"));
        }

        if (booking.getStatus() == BookingStatus.DEPOSIT_UNPAID) {
            throw new CheckInException(List.of("Deposit is unpaid."));
        }


        if (eventDateToday.getCheckedInBookingIdentifiers().contains(uuid)) {
            throw new CheckInException(List.of("Participant is already checked in."));
        }

        if (booking.getStatus() == BookingStatus.VALID) {
            booking.setStatus(BookingStatus.CHECKED_IN);
            bookingDomainDatabaseFactory.persist(booking);
        }



        eventDateToday.addBookingIdentifier(uuid);
        eventDomainDatabaseFactory.persist(event);
        return BookingListDTODatabaseMapper.INSTANCE.getView(bookingRepository.findByBookingIdentifierToken(uuid)
                .orElseThrow(() -> new BookingIdentifierNotFoundException(uuid)));
    }

    public Invoice checkOutParticipant(String identifier) throws CheckOutException, DomainStateException {
        UUID uuid = UUID.fromString(identifier);
        Booking booking = bookingDomainDatabaseFactory.toDomain(bookingRepository.findByBookingIdentifierToken(uuid)
                .orElseThrow(() -> new BookingIdentifierNotFoundException(uuid)));

        Event event = booking.getBookedEvent();
        EventDate eventDateToday = event.getEventDateForDate(LocalDate.now());

        if (booking.getStatus() == BookingStatus.CHECKED_OUT) {
            throw new CheckOutException(List.of("Participant is already checked out."));
        }

        if (booking.getStatus() != BookingStatus.CHECKED_IN) {
            throw new CheckOutException(List.of("Participant is not checked in yet."));
        }

        booking.setStatus(BookingStatus.CHECKED_OUT);
        //TODO: check if its really the deposit and if it exists
        ILineItem depositLineItem = booking.getInvoiceDeposit().getLineItems().get(0);
        //TODO: add method to easily convert all LineItems into SimpleLineItems
        SimpleLineItem negativeDepositLineItem = new SimpleLineItem(
                depositLineItem.getPrice().negate(),
                null,
                depositLineItem.getName(),
                depositLineItem.getDescription(),
                depositLineItem.getCount(),
                depositLineItem.getSubLineItems()
        );

        try {
            Invoice invoice = new Invoice(
                    booking.getFinancer(),
                    null,
                    event.getFirstEventDate(),
                    LocalDate.now(),
                    event.getLastEventDate().plusDays(30),
                    UUID.randomUUID(),
                    List.of(booking, negativeDepositLineItem),
                    booking.getInvoiceDeposit().getPaymentMethod(),
                    booking.getInvoiceDeposit().getInvoiceDelivery(),
                    booking.getFinancer(),
                    event,
                    null
            );
            booking.setInvoiceBooking(invoice);
            bookingRepository.save(bookingDomainDatabaseFactory.toEntity(booking));
        } catch(DomainStateException e) {
            throw new BackEndError("Running event is in an invalid state");
        } catch (DomainInstantiationException e) {
            throw new BackEndError("Invoice could not be generated");
        }

        return booking.getInvoiceBooking();
    }

    public void payInvoice(String identifier, @Nullable LocalDateTime date) {
        if (date == null) {
            date = LocalDateTime.now();
        }
        UUID uuid;
        try {
            uuid = UUID.fromString(identifier);
        } catch (IllegalArgumentException e) {
            throw new BackEndError("Invalid identifier");
        }

        //TODO: change exception to invoice identifier exception
        Booking booking = bookingDomainDatabaseFactory.toDomain(bookingRepository.findByInvoiceIdentifierWithFetch(uuid)
               .orElseThrow(() -> new BookingIdentifierNotFoundException(uuid)));

        boolean isDepositInvoice = booking.getInvoiceDeposit().getIdentifier().equals(uuid);
        Invoice invoice = isDepositInvoice ? booking.getInvoiceDeposit() : booking.getInvoiceBooking();

        if (invoice.isPayed()) {
            throw new BackEndError("General invoice has already been paid");
        }
        if (isDepositInvoice) {
            if (booking.getStatus() != BookingStatus.DEPOSIT_UNPAID) {
                throw new BackEndError("Booking status must be Deposit Unpaid");
            }

            booking.setStatus(BookingStatus.VALID);
            invoice.setPaymentDate(date);

        } else {
            if (booking.getStatus()!= BookingStatus.CHECKED_OUT) {
                throw new BackEndError("Booking status must be Checked Out");
            }

            invoice.setPaymentDate(date);
            booking.setStatus(BookingStatus.RESOLVED);
        }
        bookingRepository.save(bookingDomainDatabaseFactory.toEntity(booking));
    }

    public String getIdentifier(Long bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow(() -> new EntityNotFoundException(BookingEntity.class, bookingId)).getBookingIdentifier().getToken().toString();
    }

    public Invoice getDepositInvoiceForBooking(Long id) {
        //TODO: add find by id to domain factory
        return bookingDomainDatabaseFactory.toDomain(bookingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(BookingEntity.class, id))).getInvoiceDeposit();
    }

    public Invoice getGeneralInvoiceForBooking(Long id) {
        return bookingDomainDatabaseFactory.toDomain(bookingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(BookingEntity.class, id))).getInvoiceBooking();
    }
}

