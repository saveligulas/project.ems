package fhv.team11.project.ems.domain.booking;

import fhv.team11.project.ems.booking.repo.BookingStatus;
import fhv.team11.project.ems.domain.commons.ValidationMessages;
import fhv.team11.project.ems.domain.commons.exception.DomainFieldException;
import fhv.team11.project.ems.domain.commons.exception.DomainStateException;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.commons.exception.error.DomainObjectConstructorHelper;
import fhv.team11.project.ems.domain.commons.interfaces.IDomainObject;
import fhv.team11.project.ems.domain.commons.interfaces.ILineItem;
import fhv.team11.project.ems.domain.events.Event;
import fhv.team11.project.ems.domain.user.CustomerProfile;
import lombok.AccessLevel;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@NullMarked
public class Booking implements IDomainObject, ILineItem {
    private final DomainObjectConstructorHelper constructorHelper;

    @Nullable
    private Long id;
    private CustomerProfile financer;
    private Event bookedEvent;
    private int bookedPlaces;
    //TODO: move Cancellation dates to event template
    @Getter(AccessLevel.NONE)
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


    public Booking(@Nullable Long id, CustomerProfile financer, Event bookedEvent, int bookedPlaces, double price, BookingStatus status, Invoice invoiceDeposit, @Nullable Invoice invoiceBooking, UUID identifier) throws DomainValidationException {
        this.constructorHelper = new DomainObjectConstructorHelper();

        setId(id);
        setFinancer(financer);
        setBookedEvent(bookedEvent);
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

    public void setBookedEvent(Event bookedEvent) throws DomainFieldException {
        if (bookedEvent == null) {
            handleError("bookedEvent", ValidationMessages.NULL_MESSAGE, constructorHelper);
        }

        if (bookedEvent.getPlacesLeft() <= 0) {
            handleError("bookedEvent", "Not enough places left for booking", constructorHelper);
        }

        this.bookedEvent = bookedEvent;
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

    @Override
    public BigDecimal getPrice() {
        return BigDecimal.valueOf(this.price);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return bookedEvent.getEventTemplate().getName();
    }

    @Override
    public String getDescription() {
        try {
            return "Starting on " + bookedEvent.getFirstEventDate();
        } catch (DomainStateException e) {
            return null;
        }
    }

    @Override
    public Integer getCount() {
        return bookedPlaces;
    }

    @Override
    public @Nullable List<ILineItem> getSubLineItems() {
        return List.of();
    }
}
