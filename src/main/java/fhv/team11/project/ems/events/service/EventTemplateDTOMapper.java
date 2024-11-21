package fhv.team11.project.ems.events.service;

import fhv.team11.project.ems.commons.address.AddressDTOMapper;
import fhv.team11.project.ems.commons.database.IDTOEntityBiMapper;
import fhv.team11.project.ems.events.repo.EventTemplate;
import fhv.team11.project.ems.events.transfer.EventTemplateDTO;
import fhv.team11.project.ems.security.jwt.JwtSecurityContextHolder;

import java.math.BigDecimal;

public class EventTemplateDTOMapper implements IDTOEntityBiMapper<EventTemplate, EventTemplateDTO> {

    public static final EventTemplateDTOMapper INSTANCE = new EventTemplateDTOMapper();

    private EventTemplateDTOMapper() {
    }

    public EventTemplate getEntity(EventTemplateDTO dto) {
        EventTemplate eventTemplate = new EventTemplate();
        eventTemplate.setName(dto.getName());
        eventTemplate.setCategory(dto.getCategory());
        eventTemplate.setPrice(BigDecimal.valueOf(dto.getPrice()));
        eventTemplate.setMinParticipants(dto.getMinParticipants());
        eventTemplate.setMaxParticipants(dto.getMaxParticipants());
        eventTemplate.setOverbookingPlaces(2);
        eventTemplate.setAddress(AddressDTOMapper.INSTANCE.toEntity(dto.getAddress()));
        eventTemplate.setUser(JwtSecurityContextHolder.getUser());
        return eventTemplate;
    }

    public EventTemplateDTO getDTO(EventTemplate eventTemplate) {
        EventTemplateDTO dto = new EventTemplateDTO();
        dto.setName(eventTemplate.getName());
        dto.setCategory(eventTemplate.getCategory());
        dto.setAddress(AddressDTOMapper.INSTANCE.toDTO(eventTemplate.getAddress()));
        dto.setMaxParticipants(eventTemplate.getMaxParticipants());
        dto.setMinParticipants(eventTemplate.getMinParticipants());
        dto.setPrice(eventTemplate.getPrice().doubleValue());
        return dto;
    }
}
