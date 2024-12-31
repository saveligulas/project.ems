package fhv.team11.project.ems.events.mapper.presentation;

import fhv.team11.project.ems.commons.mapper.IBiPresentationDomainMapper;
import fhv.team11.project.ems.domain.adress.Appointment;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.events.EventSchedule;
import fhv.team11.project.ems.events.transfer.AppointmentDTO;
import fhv.team11.project.ems.events.transfer.EventScheduleDTO;

public class EventScheduleDTOMapper implements IBiPresentationDomainMapper<EventScheduleDTO, EventSchedule> {
    public static final EventScheduleDTOMapper INSTANCE = new EventScheduleDTOMapper();

    private EventScheduleDTOMapper() {}

    @Override
    public EventScheduleDTO getView(EventSchedule domain) {
        EventScheduleDTO dto = new EventScheduleDTO();

        for (Appointment appointment : domain.getAppointments()) {
            dto.addAppointment(AppointmentDTOMapper.INSTANCE.getView(appointment));
        }

        return dto;
    }

    @Override
    public EventSchedule getDomain(EventScheduleDTO presentationObject) throws DomainValidationException {
        EventSchedule eventSchedule = new EventSchedule();
        for (AppointmentDTO appointmentDTO : presentationObject.getAppointments()) {
            eventSchedule.addAppointment(AppointmentDTOMapper.INSTANCE.getDomain(appointmentDTO));
        }
        return eventSchedule;
    }
}
