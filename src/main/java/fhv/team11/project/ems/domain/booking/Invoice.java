package fhv.team11.project.ems.domain.booking;

import fhv.team11.project.ems.domain.commons.interfaces.IDomainObject;
import fhv.team11.project.ems.domain.commons.interfaces.IPayable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Invoice implements IDomainObject, IPayable {
    private Long id;
    private InvoiceDelivery invoiceDelivery;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private double amount;
    private UUID identifier;
    private LocalDate dueDate;
    private LocalDateTime paymentDate;
    //TODO implement LineItems Interface with prices and add subtotals and total price here
}
