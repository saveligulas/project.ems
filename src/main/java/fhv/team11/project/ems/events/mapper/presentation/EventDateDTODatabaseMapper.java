package fhv.team11.project.ems.events.mapper.presentation;

import fhv.team11.project.ems.commons.database.IPresentationDatabaseMapper;
import fhv.team11.project.ems.events.repo.EventDateEntity;
import fhv.team11.project.ems.events.transfer.EventDateDTO;

public class EventDateDTODatabaseMapper implements IPresentationDatabaseMapper<EventDateDTO, EventDateEntity> {
    public static final EventDateDTODatabaseMapper INSTANCE = new EventDateDTODatabaseMapper();
    
    private EventDateDTODatabaseMapper() {
    }
    
    @Override
    public EventDateDTO getView(EventDateEntity entity) {
        EventDateDTO eventDateDTO = new EventDateDTO();
        eventDateDTO.setDate(entity.getDate());
        eventDateDTO.setName(entity.getName());
        eventDateDTO.setEventSchedule(EventScheduleDTODatabaseMapper.INSTANCE.getView(entity.getSchedule()));
        return eventDateDTO;
    }
}
