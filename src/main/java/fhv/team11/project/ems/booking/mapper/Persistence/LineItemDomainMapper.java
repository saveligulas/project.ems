package fhv.team11.project.ems.booking.mapper.Persistence;

import fhv.team11.project.ems.booking.repo.LineItemEntity;
import fhv.team11.project.ems.commons.domain.ISimpleDomainDatabaseMapper;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.commons.interfaces.ILineItem;
import fhv.team11.project.ems.domain.commons.interfaces.SimpleLineItem;

import java.util.ArrayList;
import java.util.List;

public class LineItemDomainMapper implements ISimpleDomainDatabaseMapper<ILineItem, LineItemEntity> {
    public static final LineItemDomainMapper INSTANCE = new LineItemDomainMapper();
    
    private LineItemDomainMapper() {
    }
    
    @Override
    public LineItemEntity toEntity(ILineItem domain) {
        LineItemEntity lineItemEntity = new LineItemEntity();
        lineItemEntity.setId(domain.getId());
        lineItemEntity.setName(domain.getName());
        lineItemEntity.setDescription(domain.getDescription());
        lineItemEntity.setPrice(domain.getPrice());
        lineItemEntity.setCount(domain.getCount());
        lineItemEntity.setCount(domain.getCount());
        //TODO: Use Recursion to avoid infinite loop
        List<LineItemEntity> lineItemEntities = new ArrayList<>();
        if (domain.getSubLineItems() != null && !domain.getSubLineItems().isEmpty()) {
            for (ILineItem lineItem : domain.getSubLineItems()) {
                lineItemEntities.add(this.toEntity(lineItem));
            }
        }
        lineItemEntity.setSubLineItems(lineItemEntities);
        return lineItemEntity;
    }

    @Override
    public ILineItem toDomain(LineItemEntity entity) {
        return new SimpleLineItem(
                entity.getPrice(),
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCount(),
                entity.getSubLineItems() == null ? null : entity.getSubLineItems().stream().map(LineItemDomainMapper.INSTANCE::toDomain).toList()
        );
    }
}
