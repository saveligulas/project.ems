package fhv.team11.project.ems.domain.booking;

import fhv.team11.project.ems.domain.commons.exception.DomainInstantiationException;
import fhv.team11.project.ems.domain.commons.exception.error.DomainObjectConstructorHelper;
import fhv.team11.project.ems.domain.commons.interfaces.IDomainObject;
import fhv.team11.project.ems.domain.commons.interfaces.ILineItem;
import fhv.team11.project.ems.domain.commons.interfaces.IPayable;
import fhv.team11.project.ems.domain.commons.interfaces.IRepresentRealWorldEntity;
import fhv.team11.project.ems.domain.user.SellerProfile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Invoice implements IDomainObject, IPayable {
    private final DomainObjectConstructorHelper constructorHelper;

    private Long id;
    private IRepresentRealWorldEntity seller;
    private IRepresentRealWorldEntity buyer;
    private InvoiceDelivery invoiceDelivery;
    private PaymentMethod paymentMethod;
    private List<ILineItem> lineItems;
    private InvoiceIdentifier identifier;
    private LocalDate dueDate;
    private LocalDate createdDate;
    private LocalDate supplyDate;
    private LocalDateTime paymentDate;

    public Invoice(LocalDateTime paymentDate, LocalDate supplyDate, LocalDate createdDate, LocalDate dueDate, InvoiceIdentifier identifier, List<ILineItem> lineItems, PaymentStatus paymentStatus, PaymentMethod paymentMethod, InvoiceDelivery invoiceDelivery, IRepresentRealWorldEntity buyer, IRepresentRealWorldEntity seller, Long id) throws DomainInstantiationException {
        this.constructorHelper = new DomainObjectConstructorHelper();

        setId(id);
        setSeller(seller);
        setBuyer(buyer);
        setInvoiceDelivery(invoiceDelivery);
        setPaymentMethod(paymentMethod);
        setLineItems(lineItems);
        setIdentifier(identifier);
        setDueDate(dueDate);
        setCreatedDate(createdDate);
        setSupplyDate(supplyDate);
        setPaymentDate(paymentDate);

        constructorHelper.finish();
    }



    public void setId(Long id) {
        this.id = id;
    }

    public void setSeller(IRepresentRealWorldEntity seller) {
        this.seller = seller;
    }

    public void setBuyer(IRepresentRealWorldEntity buyer) {
        this.buyer = buyer;
    }

    public void setInvoiceDelivery(InvoiceDelivery invoiceDelivery) {
        this.invoiceDelivery = invoiceDelivery;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setLineItems(List<ILineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public void setIdentifier(InvoiceIdentifier identifier) {
        this.identifier = identifier;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public void setSupplyDate(LocalDate supplyDate) {
        this.supplyDate = supplyDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentStatus getPaymentStatus() {
        return null;
    }

    @Override
    public void pay() {

    }
}
