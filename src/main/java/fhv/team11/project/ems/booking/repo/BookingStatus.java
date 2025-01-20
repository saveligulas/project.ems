package fhv.team11.project.ems.booking.repo;

public enum BookingStatus {
    INVALID("Invalid","Booking has become invalid due to the event not being available anymore."),
    DEPOSIT_UNPAID("Deposit Unpaid", "Deposit Invoice has to be paid before the event starts."),
    VALID("Deposit Paid", "Deposit was paid before the event started."),
    CHECKED_IN("Checked In", "Customer has checked in at event."),
    CHECKED_OUT("Checked Out", "Customer has checked out of event and was presented the invoice."),
    UNRESOLVED("Booking Unpaid", "Invoice presented at Check Out was not yet paid."),
    RESOLVED("Booking Paid", "Invoice for booking was paid in the given time.");

    private final String description;
    private final String title;

    BookingStatus(String title, String description) {
        this.description = description;
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
}
