package fhv.team11.project.ems.events.mapper.persistence;

import fhv.team11.project.ems.commons.domain.ISimpleDomainDatabaseMapper;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.events.EventDate;
import fhv.team11.project.ems.events.repo.EventDateEntity;
import org.jspecify.annotations.NullMarked;


@NullMarked
public class EventDateDomainMapper implements ISimpleDomainDatabaseMapper<EventDate, EventDateEntity> {

    public static final EventDateDomainMapper INSTANCE = new EventDateDomainMapper();

    private EventDateDomainMapper() {}

    @Override
    public EventDateEntity toEntity(EventDate domain) {
        return new EventDateEntity(
                domain.getId(),
                domain.getDate(),
                domain.getName(),
                EventScheduleDomainMapper.INSTANCE.toEntity(domain.getSchedule()));
    }

    @Override
    public EventDate toDomain(EventDateEntity entity) throws DomainValidationException {
        return new EventDate(
                entity.getId(),
                entity.getDate(),
                entity.getName(),
                EventScheduleDomainMapper.INSTANCE.toDomain(entity.getSchedule()));
    }
}
