package fhv.team11.project.ems.events.mapper.persistence;

import fhv.team11.project.ems.commons.domain.ISimpleDomainDatabaseMapper;
import fhv.team11.project.ems.domain.adress.Appointment;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.events.repo.AppointmentEntity;

public class AppointmentDomainMapper implements ISimpleDomainDatabaseMapper<Appointment, AppointmentEntity> {
    public static final AppointmentDomainMapper INSTANCE = new AppointmentDomainMapper();
    
    private AppointmentDomainMapper() {
    }
    
    
    @Override
    public AppointmentEntity toEntity(Appointment domain) {
        AppointmentEntity entity = new AppointmentEntity();
        entity.setId(null);
        entity.setStartTime(domain.getStartTime());
        entity.setEndTime(domain.getEndTime());
        entity.setTitle(domain.getTitle());
        entity.setDescription(domain.getDescription());
        return entity;
    }

    @Override
    public Appointment toDomain(AppointmentEntity entity) throws DomainValidationException {
        return new Appointment(
                entity.getId(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getTitle(),
                entity.getDescription()
        );
    }
}
