package fhv.team11.project.ems.events.service;

import fhv.team11.project.ems.events.repo.EventTemplate;
import fhv.team11.project.ems.events.repo.EventTemplateRepository;
import fhv.team11.project.ems.events.transfer.EventTemplateDTO;
import fhv.team11.project.ems.security.jwt.JwtSecurityContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EventTemplateService {

    private final EventTemplateRepository eventTemplateRepository;

    @Autowired
    public EventTemplateService(EventTemplateRepository eventTemplateRepository) {
        this.eventTemplateRepository = eventTemplateRepository;
    }

    public void createNewBlueprint(EventTemplateDTO eventTemplateDTO) {
        EventTemplate eventTemplate = EventTemplateDTOMapper.INSTANCE.getEntity(eventTemplateDTO);
        eventTemplateRepository.persist(eventTemplate);
    }

    public List<EventTemplateDTO> getListOfBlueprints(int pageNumber, int pageSize) {
        return eventTemplateRepository.getEventTemplatesForPageNumber(pageNumber, pageSize, JwtSecurityContextHolder.getUser().getId())
                .stream()
                .map(EventTemplateDTOMapper.INSTANCE::getDTO)
                .toList();
    }

    public EventTemplateDTO getTemplateByName(String name) {
        return EventTemplateDTOMapper.INSTANCE.getDTO(eventTemplateRepository.getEventTemplateByName(name));
    }
}
