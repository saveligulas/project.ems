package fhv.team11.project.ems.domain.booking;

import fhv.team11.project.ems.domain.commons.exception.DomainInstantiationException;
import fhv.team11.project.ems.domain.commons.exception.error.DomainObjectConstructorHelper;
import fhv.team11.project.ems.domain.commons.interfaces.IDomainObject;
import fhv.team11.project.ems.domain.commons.interfaces.ILineItem;
import fhv.team11.project.ems.domain.commons.interfaces.IPayable;
import fhv.team11.project.ems.domain.commons.interfaces.IRepresentRealWorldEntity;
import fhv.team11.project.ems.domain.user.CustomerProfile;
import fhv.team11.project.ems.domain.user.SellerProfile;
import lombok.Getter;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
public class Invoice implements IDomainObject {
    private final DomainObjectConstructorHelper constructorHelper;

    private Long id;
    private CustomerProfile customerProfile;
    private IRepresentRealWorldEntity seller;
    private IRepresentRealWorldEntity buyer;
    private InvoiceDelivery invoiceDelivery;
    private PaymentMethod paymentMethod;
    private List<ILineItem> lineItems;
    private UUID identifier;
    private LocalDate dueDate;
    private LocalDate createdDate;
    private LocalDate supplyDate;
    @Nullable
    private LocalDateTime paymentDate;

    public Invoice(CustomerProfile customerProfile, @Nullable LocalDateTime paymentDate, LocalDate supplyDate, LocalDate createdDate, LocalDate dueDate, UUID identifier, List<ILineItem> lineItems, PaymentMethod paymentMethod, InvoiceDelivery invoiceDelivery, IRepresentRealWorldEntity buyer, IRepresentRealWorldEntity seller, Long id) throws DomainInstantiationException {
        this.constructorHelper = new DomainObjectConstructorHelper();

        this.customerProfile = customerProfile;
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

    public void setIdentifier(UUID identifier) {
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

    public boolean isPayed() {
        return this.paymentDate != null;
    }
}
