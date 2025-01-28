package fhv.team11.project.ems.booking.mapper.Persistence;

import fhv.team11.project.ems.booking.repo.RealWorldEntity;
import fhv.team11.project.ems.commons.address.AddressDomainDatabaseFactory;
import fhv.team11.project.ems.commons.domain.ISimpleDomainDatabaseMapper;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.commons.interfaces.IRepresentRealWorldEntity;

public class RealWorldEntityDomainMapper implements ISimpleDomainDatabaseMapper<IRepresentRealWorldEntity, RealWorldEntity> {
    public static final RealWorldEntityDomainMapper INSTANCE = new RealWorldEntityDomainMapper();
    
    private RealWorldEntityDomainMapper() {
    }
    
    @Override
    public RealWorldEntity toEntity(IRepresentRealWorldEntity domain) {
        RealWorldEntity entity = new RealWorldEntity();
        entity.setId(null);
        entity.setSurname(domain.getSurname());
        entity.setName(domain.getName());
        entity.setAddress(AddressDomainDatabaseFactory.INSTANCE.toEntity(domain.getAddress()));
        return entity;
    }

    @Override
    public IRepresentRealWorldEntity toDomain(RealWorldEntity entity) throws DomainValidationException {
        return null;
    }
}
