package fhv.team11.project.ems.booking;

import fhv.team11.project.ems.booking.repo.InvoiceEntity;
import fhv.team11.project.ems.commons.domain.DomainDatabaseFactory;
import fhv.team11.project.ems.commons.domain.ISimpleDomainDatabaseMapper;
import fhv.team11.project.ems.domain.booking.Invoice;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import org.springframework.stereotype.Component;

@Component
public class InvoiceDomainDatabaseFactory extends DomainDatabaseFactory implements ISimpleDomainDatabaseMapper<Invoice, InvoiceEntity> {
    @Override
    public InvoiceEntity toEntity(Invoice domain) {
        return null;
    }

    @Override
    public Invoice toDomain(InvoiceEntity entity) throws DomainValidationException {
        return null;
    }
}
