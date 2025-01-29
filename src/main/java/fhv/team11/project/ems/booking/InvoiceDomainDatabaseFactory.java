package fhv.team11.project.ems.booking;

import fhv.team11.project.ems.booking.mapper.Persistence.LineItemDomainMapper;
import fhv.team11.project.ems.booking.mapper.Persistence.RealWorldEntityDomainMapper;
import fhv.team11.project.ems.booking.repo.InvoiceEntity;
import fhv.team11.project.ems.booking.repo.InvoiceEntityRepository;
import fhv.team11.project.ems.commons.address.AddressDomainDatabaseFactory;
import fhv.team11.project.ems.commons.domain.DomainDatabaseFactory;
import fhv.team11.project.ems.commons.domain.IHandleDomainPersistence;
import fhv.team11.project.ems.commons.domain.IHandleDomainPersistenceShallow;
import fhv.team11.project.ems.commons.domain.ISimpleDomainDatabaseMapper;
import fhv.team11.project.ems.customer.CustomerProfileDomainDatabaseFactory;
import fhv.team11.project.ems.domain.booking.Invoice;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class InvoiceDomainDatabaseFactory extends DomainDatabaseFactory implements ISimpleDomainDatabaseMapper<Invoice, InvoiceEntity>, IHandleDomainPersistenceShallow<Invoice> {
    private final CustomerProfileDomainDatabaseFactory customerProfileDomainDatabaseFactory;
    private final AddressDomainDatabaseFactory addressDomainDatabaseFactory;
    private final InvoiceEntityRepository invoiceEntityRepository;

    public InvoiceDomainDatabaseFactory(CustomerProfileDomainDatabaseFactory customerProfileDomainDatabaseFactory, AddressDomainDatabaseFactory addressDomainDatabaseFactory, InvoiceEntityRepository invoiceEntityRepository) {
        super();
        this.customerProfileDomainDatabaseFactory = customerProfileDomainDatabaseFactory;
        this.addressDomainDatabaseFactory = addressDomainDatabaseFactory;
        this.invoiceEntityRepository = invoiceEntityRepository;
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
    public @Nullable Invoice toDomain(InvoiceEntity entity) throws DomainValidationException {
        if (entity == null) {
            return null;
        }

        return new Invoice(
                customerProfileDomainDatabaseFactory.toDomain(entity.getCustomerProfile()),
                entity.getPaymentDate(),
                entity.getSupplyDate(),
                entity.getCreatedDate(),
                entity.getDueDate(),
                entity.getIdentifier(),
                entity.getLineItems().stream()
                        .map(LineItemDomainMapper.INSTANCE::toDomain)
                        .toList(),
                entity.getPaymentMethod(),
                entity.getInvoiceDelivery(),
                RealWorldEntityDomainMapper.INSTANCE.toDomain(entity.getBuyer()),
                RealWorldEntityDomainMapper.INSTANCE.toDomain(entity.getSeller()),
                entity.getId()
        );
    }

    @Override
    public void persist(Invoice domainObject) throws DomainValidationException {
        invoiceEntityRepository.save(Objects.requireNonNull(toEntity(domainObject)));
    }
}
