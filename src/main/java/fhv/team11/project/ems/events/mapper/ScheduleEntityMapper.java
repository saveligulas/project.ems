package fhv.team11.project.ems.events.mapper;

import fhv.team11.project.ems.domain.commons.exception.DomainFieldValidationException;
import fhv.team11.project.ems.domain.events.ScheduleEventModel;
import fhv.team11.project.ems.domain.events.TemplateModel;
import fhv.team11.project.ems.events.repo.Appointment;
import fhv.team11.project.ems.events.repo.Schedule;
import fhv.team11.project.ems.events.transfer.EventTemplateDTO;
import fhv.team11.project.ems.events.transfer.ScheduleEventDTO;

public class ScheduleEntityMapper {

    public static final ScheduleEntityMapper INSTANCE = new ScheduleEntityMapper();

    public Appointment toEntity(ScheduleEventModel scheduleEventModel) {
        Appointment appointment = new Appointment();

        appointment.setStartTime(scheduleEventModel.getStartTime());
        appointment.setEndTime(scheduleEventModel.getEndTime());
        appointment.setId(scheduleEventModel.getId());

        return appointment;
    }

    public ScheduleEventModel toModel(Appointment appointment) throws DomainFieldValidationException {
        return new ScheduleEventModel(appointment.getId(), appointment.getStartTime(), appointment.getEndTime());
    }
}
