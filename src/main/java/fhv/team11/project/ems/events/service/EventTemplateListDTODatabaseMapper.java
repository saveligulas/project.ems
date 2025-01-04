package fhv.team11.project.ems.events.service;

import fhv.team11.project.ems.commons.database.IPresentationDatabaseMapper;
import fhv.team11.project.ems.commons.mapper.IDomainPresentationMapper;
import fhv.team11.project.ems.events.repo.EventTemplateEntity;
import fhv.team11.project.ems.events.transfer.EventTemplateListDTO;

public class EventTemplateListDTODatabaseMapper implements IPresentationDatabaseMapper<EventTemplateListDTO, EventTemplateEntity> {

    public static final EventTemplateListDTODatabaseMapper INSTANCE = new EventTemplateListDTODatabaseMapper();

    private EventTemplateListDTODatabaseMapper() {
    }

    @Override
    public EventTemplateListDTO getView(EventTemplateEntity entity) {
        EventTemplateListDTO listDTO = new EventTemplateListDTO();
        listDTO.setId(entity.getId());
        listDTO.setName(entity.getName());
        return listDTO;
    }
}
