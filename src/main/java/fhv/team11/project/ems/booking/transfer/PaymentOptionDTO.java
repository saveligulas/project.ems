package fhv.team11.project.ems.booking.transfer;

import fhv.team11.project.ems.domain.booking.InvoiceDelivery;
import fhv.team11.project.ems.domain.booking.PaymentMethod;
import lombok.Data;

@Data
public class PaymentOptionDTO {
    private PaymentMethod paymentMethod;
    private InvoiceDelivery invoiceDelivery;
}
