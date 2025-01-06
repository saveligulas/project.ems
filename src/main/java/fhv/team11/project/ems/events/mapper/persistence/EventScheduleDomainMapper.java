package fhv.team11.project.ems.events.mapper.persistence;

import fhv.team11.project.ems.commons.domain.ISimpleDomainDatabaseMapper;
import fhv.team11.project.ems.domain.adress.Appointment;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.events.EventSchedule;
import fhv.team11.project.ems.events.repo.AppointmentEntity;
import fhv.team11.project.ems.events.repo.Schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventScheduleDomainMapper implements ISimpleDomainDatabaseMapper<EventSchedule, Schedule> {
    public static final EventScheduleDomainMapper INSTANCE = new EventScheduleDomainMapper();
    
    private EventScheduleDomainMapper() {
    }
    
    @Override
    public Schedule toEntity(EventSchedule domain) {
        Schedule schedule = new Schedule();
        schedule.setId(null);
        schedule.setAppointments(domain.getAppointments()
                .stream()
                .map(AppointmentDomainMapper.INSTANCE::toEntity)
                .collect(Collectors.toSet()));
        return schedule;
    }

    @Override
    public EventSchedule toDomain(Schedule entity) throws DomainValidationException {
        List<Appointment> appointments = new ArrayList<>();

        for (AppointmentEntity app : entity.getAppointments()) {
            appointments.add(AppointmentDomainMapper.INSTANCE.toDomain(app));
        }

        return new EventSchedule(
                entity.getId(),
                appointments
        );
    }
}
