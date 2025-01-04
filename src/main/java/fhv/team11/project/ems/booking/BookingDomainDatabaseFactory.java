package fhv.team11.project.ems.booking;

import fhv.team11.project.ems.booking.repo.BookingEntity;
import fhv.team11.project.ems.booking.repo.BookingIdentifier;
import fhv.team11.project.ems.booking.repo.BookingRepository;
import fhv.team11.project.ems.booking.transfer.BookingView;
import fhv.team11.project.ems.commons.domain.DomainDatabaseFactory;
import fhv.team11.project.ems.commons.domain.IHandleDomainPersistenceShallow;
import fhv.team11.project.ems.commons.domain.ISimpleDomainDatabaseMapper;
import fhv.team11.project.ems.commons.mapper.IBiPresentationDomainMapper;
import fhv.team11.project.ems.customer.CustomerProfileDomainDatabaseFactory;
import fhv.team11.project.ems.domain.booking.Booking;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.events.EventDomainDatabaseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class BookingDomainDatabaseFactory extends DomainDatabaseFactory implements IHandleDomainPersistenceShallow<Booking>, ISimpleDomainDatabaseMapper<Booking, BookingEntity> {
    private final CustomerProfileDomainDatabaseFactory customerProfileDomainDatabaseFactory;
    private final EventDomainDatabaseFactory eventDomainDatabaseFactory;
    private final InvoiceDomainDatabaseFactory invoiceDomainDatabaseFactory;
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingDomainDatabaseFactory(CustomerProfileDomainDatabaseFactory customerProfileDomainDatabaseFactory, EventDomainDatabaseFactory eventDomainDatabaseFactory, InvoiceDomainDatabaseFactory invoiceDomainDatabaseFactory, BookingRepository bookingRepository) {
        this.customerProfileDomainDatabaseFactory = customerProfileDomainDatabaseFactory;
        this.eventDomainDatabaseFactory = eventDomainDatabaseFactory;
        this.invoiceDomainDatabaseFactory = invoiceDomainDatabaseFactory;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void persist(Booking domainObject) throws DomainValidationException {
        bookingRepository.save(toEntity(domainObject));
    }

    @Override
    public BookingEntity toEntity(Booking domain) {
        BookingEntity entity = new BookingEntity();
        entity.setId(domain.getId());
        entity.setFinancer(customerProfileDomainDatabaseFactory.toEntity(domain.getFinancer()));
        entity.setBookedEvent(eventDomainDatabaseFactory.toEntity(domain.getEvent()));
        entity.setBookedPlaces(domain.getBookedPlaces());
        entity.setPrice(BigDecimal.valueOf(domain.getPrice()).setScale(2, RoundingMode.FLOOR));
        entity.setStatus(domain.getStatus());
        entity.setDeposit(invoiceDomainDatabaseFactory.toEntity(domain.getInvoiceDeposit()));
        entity.setBooking(invoiceDomainDatabaseFactory.toEntity(domain.getInvoiceBooking()));

        BookingIdentifier identifier = new BookingIdentifier();
        identifier.setToken(domain.getIdentifier());
        identifier.setBooking(entity);

        entity.setBookingIdentifier(identifier);

        return entity;
    }

    @Override
    public Booking toDomain(BookingEntity entity) throws DomainValidationException {
        return null;
    }
}
