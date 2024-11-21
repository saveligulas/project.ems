package fhv.team11.project.ems.events.service;

import fhv.team11.project.ems.commons.database.IDTOEntityBiMapper;

import fhv.team11.project.ems.commons.database.IEntityDTOMapper;
import fhv.team11.project.ems.events.repo.Appointment;
import fhv.team11.project.ems.events.repo.EventDate;
import fhv.team11.project.ems.events.repo.Schedule;
import fhv.team11.project.ems.events.transfer.ActiveEventDateDTO;
import fhv.team11.project.ems.events.transfer.ActiveEventWizardDTO;

public class AppointmentDTOMapper implements IDTOEntityBiMapper<Appointment, ActiveEventWizardDTO> {

    public static final AppointmentDTOMapper INSTANCE = new AppointmentDTOMapper();

    public AppointmentDTOMapper() {}

    public Appointment getEntity(ActiveEventWizardDTO dto) {
        Appointment appointment = new Appointment();
        appointment.setStartTime(dto.getScheduleEvent().getStartTime());
        appointment.setEndTime((dto.getScheduleEvent().getEndTime()));
        return appointment;
    }

    @Override
    public ActiveEventWizardDTO getDTO(Appointment entity) {
        return null;
    }
}
