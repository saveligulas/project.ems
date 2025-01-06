package fhv.team11.project.ems.events.mapper.presentation;

import fhv.team11.project.ems.commons.database.IPresentationDatabaseMapper;
import fhv.team11.project.ems.events.repo.Schedule;
import fhv.team11.project.ems.events.transfer.EventScheduleDTO;

public class EventScheduleDTODatabaseMapper implements IPresentationDatabaseMapper<EventScheduleDTO, Schedule> {
    public static final EventScheduleDTODatabaseMapper INSTANCE = new EventScheduleDTODatabaseMapper();

    private EventScheduleDTODatabaseMapper() {
    }

    @Override
    public EventScheduleDTO getView(Schedule entity) {
        EventScheduleDTO eventScheduleDTO = new EventScheduleDTO();
        eventScheduleDTO.setAppointments(entity.getAppointments().stream()
                .map(AppointmentDTODatabaseMapper.INSTANCE::getView)
                .toList());
        return eventScheduleDTO;
    }
}
