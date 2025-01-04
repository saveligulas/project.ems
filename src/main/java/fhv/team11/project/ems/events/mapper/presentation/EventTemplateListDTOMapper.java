package fhv.team11.project.ems.events.mapper.presentation;

import fhv.team11.project.ems.commons.mapper.IDomainPresentationMapper;
import fhv.team11.project.ems.commons.mapper.IPresentationDomainMapper;
import fhv.team11.project.ems.domain.events.EventTemplate;
import fhv.team11.project.ems.events.transfer.EventTemplateListDTO;

public class EventTemplateListDTOMapper implements IDomainPresentationMapper<EventTemplateListDTO, EventTemplate> {
    @Override
    public EventTemplateListDTO getView(EventTemplate domain) {
        EventTemplateListDTO eventTemplateListDTO = new EventTemplateListDTO();
        eventTemplateListDTO.setId(domain.getId());
        eventTemplateListDTO.setName(domain.getName());
        return eventTemplateListDTO;
    }
}
