package fhv.team11.project.ems.events.service;

import fhv.team11.project.ems.commons.mapper.IDomainPresentationMapper;
import fhv.team11.project.ems.events.repo.EventTemplateEntity;
import fhv.team11.project.ems.events.transfer.EventTemplateListDTO;

public class EventTemplateListDTOMapper implements IDomainPresentationMapper<EventTemplateListDTO, EventTemplateEntity> {

    public static final EventTemplateListDTOMapper INSTANCE = new EventTemplateListDTOMapper();

    private EventTemplateListDTOMapper() {
    }

    @Override
    public EventTemplateListDTO getView(EventTemplateEntity entity) {
        EventTemplateListDTO listDTO = new EventTemplateListDTO();
        listDTO.setId(entity.getId());
        listDTO.setName(entity.getName());
        return listDTO;
    }
}
