package fhv.team11.project.ems.booking;

import fhv.team11.project.ems.booking.mapper.Persistence.LineItemDomainMapper;
import fhv.team11.project.ems.booking.mapper.Persistence.RealWorldEntityDomainMapper;
import fhv.team11.project.ems.booking.repo.InvoiceEntity;
import fhv.team11.project.ems.commons.address.AddressDomainDatabaseFactory;
import fhv.team11.project.ems.commons.domain.DomainDatabaseFactory;
import fhv.team11.project.ems.commons.domain.ISimpleDomainDatabaseMapper;
import fhv.team11.project.ems.customer.CustomerProfileDomainDatabaseFactory;
import fhv.team11.project.ems.domain.booking.Invoice;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;

@Component
public class InvoiceDomainDatabaseFactory extends DomainDatabaseFactory implements ISimpleDomainDatabaseMapper<Invoice, InvoiceEntity> {
    private final CustomerProfileDomainDatabaseFactory customerProfileDomainDatabaseFactory;
    private final AddressDomainDatabaseFactory addressDomainDatabaseFactory;

    public InvoiceDomainDatabaseFactory(CustomerProfileDomainDatabaseFactory customerProfileDomainDatabaseFactory, AddressDomainDatabaseFactory addressDomainDatabaseFactory) {
        super();
        this.customerProfileDomainDatabaseFactory = customerProfileDomainDatabaseFactory;
        this.addressDomainDatabaseFactory = addressDomainDatabaseFactory;
    }

    @Override
    public @Nullable InvoiceEntity toEntity(Invoice domain) {
        if (domain == null) {
            return null;
        }

        InvoiceEntity entity = new InvoiceEntity();
        entity.setId(domain.getId());
        entity.setCustomerProfile(customerProfileDomainDatabaseFactory.toEntity(domain.getCustomerProfile()));
        entity.setSeller(RealWorldEntityDomainMapper.INSTANCE.toEntity(domain.getSeller()));
        entity.setBuyer(RealWorldEntityDomainMapper.INSTANCE.toEntity(domain.getBuyer()));
        entity.setInvoiceDelivery(domain.getInvoiceDelivery());
        entity.setPaymentMethod(domain.getPaymentMethod());
        entity.setLineItems(domain.getLineItems().stream().map(LineItemDomainMapper.INSTANCE::toEntity).toList());
        entity.setIdentifier(domain.getIdentifier());
        entity.setDueDate(domain.getDueDate());
        entity.setCreatedDate(domain.getCreatedDate());
        entity.setSupplyDate(domain.getSupplyDate());
        entity.setPaymentDate(domain.getPaymentDate());
        return entity;
    }

    @Override
    public Invoice toDomain(InvoiceEntity entity) throws DomainValidationException {
        return null;
    }
}
