package fhv.team11.project.ems.events.service;

import fhv.team11.project.ems.commons.database.IEntityDTOMapper;
import fhv.team11.project.ems.events.repo.EventTemplate;
import fhv.team11.project.ems.events.transfer.EventTemplateListDTO;

public class EventTemplateListDTOMapper implements IEntityDTOMapper<EventTemplate, EventTemplateListDTO> {

    public static final EventTemplateListDTOMapper INSTANCE = new EventTemplateListDTOMapper();

    private EventTemplateListDTOMapper() {
    }

    @Override
    public EventTemplateListDTO getDTO(EventTemplate entity) {
        EventTemplateListDTO listDTO = new EventTemplateListDTO();
        listDTO.setId(entity.getId());
        listDTO.setName(entity.getName());
        return listDTO;
    }
}
