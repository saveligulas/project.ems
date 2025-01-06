package fhv.team11.project.ems.domain.booking;

import fhv.team11.project.ems.booking.repo.BookingStatus;
import fhv.team11.project.ems.domain.commons.ValidationMessages;
import fhv.team11.project.ems.domain.commons.exception.DomainFieldException;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.commons.exception.error.DomainObjectConstructorHelper;
import fhv.team11.project.ems.domain.commons.interfaces.IDomainObject;
import fhv.team11.project.ems.domain.events.Event;
import fhv.team11.project.ems.domain.user.CustomerProfile;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NullMarked
public class Booking implements IDomainObject {
    private final DomainObjectConstructorHelper constructorHelper;

    @Nullable
    private Long id;
    private CustomerProfile financer;
    private Event event;
    private int bookedPlaces;
    //TODO: move Cancellation dates to event template
    private double price;
    private BookingStatus status;
    private Invoice invoiceDeposit;
    @Nullable
    private Invoice invoiceBooking;
    private UUID identifier;

    @Nullable
    private String cancellationReason;
    @Nullable
    private LocalDateTime cancellationDate;


    public Booking(@Nullable Long id, CustomerProfile financer, Event event, int bookedPlaces, double price, BookingStatus status, Invoice invoiceDeposit, @Nullable Invoice invoiceBooking, UUID identifier) throws DomainValidationException {
        this.constructorHelper = new DomainObjectConstructorHelper();

        setId(id);
        setFinancer(financer);
        setEvent(event);
        setBookedPlaces(bookedPlaces);
        setPrice(price);
        setStatus(status);
        setInvoiceDeposit(invoiceDeposit);
        setInvoiceBooking(invoiceBooking);
        setIdentifier(identifier);

        constructorHelper.finish();
    }

    public void setId(@Nullable Long id) throws DomainFieldException {
        validateId(id, constructorHelper);
        this.id = id;
    }

    public void setFinancer(CustomerProfile financer) throws DomainFieldException {
        if (financer == null) {
            handleError("financer", ValidationMessages.NULL_MESSAGE, constructorHelper);
        }

        this.financer = financer;
    }

    public void setEvent(Event event) throws DomainFieldException {
        if (event == null) {
            handleError("bookedEvent", ValidationMessages.NULL_MESSAGE, constructorHelper);
        }
        this.event = event;
    }

    public void setBookedPlaces(int bookedPlaces) {
        this.bookedPlaces = bookedPlaces;
    }


    public void setPrice(double price) {
        this.price = price;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public void setInvoiceDeposit(Invoice invoiceDeposit) {
        this.invoiceDeposit = invoiceDeposit;
    }

    public void setInvoiceBooking(@Nullable Invoice invoiceBooking) {
        this.invoiceBooking = invoiceBooking;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }
}
