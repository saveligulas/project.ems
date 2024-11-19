package fhv.team11.project.ems.events.service;

import fhv.team11.project.ems.commons.error.EntityNotFoundException;
import fhv.team11.project.ems.events.repo.EventTemplate;
import fhv.team11.project.ems.events.repo.EventTemplateRepository;
import fhv.team11.project.ems.events.transfer.EventTemplateDTO;
import fhv.team11.project.ems.events.transfer.EventTemplateListDTO;
import fhv.team11.project.ems.security.error.AuthenticationErrorException;
import fhv.team11.project.ems.security.jwt.JwtSecurityContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EventTemplateService {

    private final EventTemplateRepository eventTemplateRepository;

    @Autowired
    public EventTemplateService(EventTemplateRepository eventTemplateRepository) {
        this.eventTemplateRepository = eventTemplateRepository;
    }

    private boolean hasAccess(EventTemplate eventTemplate) {
        return eventTemplate.getUser().getId().equals(JwtSecurityContextHolder.getUser().getId());
    }

    public void createNewTemplate(EventTemplateDTO eventTemplateDTO) {
        EventTemplate eventTemplate = EventTemplateDTOMapper.INSTANCE.getEntity(eventTemplateDTO);
        eventTemplateRepository.persist(eventTemplate);
    }

    public List<EventTemplateListDTO> getListOfTemplates(int pageNumber, int pageSize) {
        return eventTemplateRepository.getEventTemplatesForPageNumber(pageNumber, pageSize, JwtSecurityContextHolder.getUser().getId())
                .stream()
                .map(EventTemplateListDTOMapper.INSTANCE::getDTO)
                .toList();
    }
    public EventTemplateListDTO getTemplateListByID(long templateId) {
        return eventTemplateRepository.findById(templateId)
                .map(EventTemplateListDTOMapper.INSTANCE::getDTO)
                .orElse(null);
    }
    public EventTemplateDTO getTemplateByName(String name) {
        return EventTemplateDTOMapper.INSTANCE.getDTO(eventTemplateRepository.getEventTemplateByName(name));
    }

    public EventTemplateDTO getTemplateById(Long templateId) {
        EventTemplate eventTemplate = eventTemplateRepository.findById(templateId)
                .orElseThrow(() -> new EntityNotFoundException(EventTemplate.class, templateId));

        if (!hasAccess(eventTemplate)) {
            throw new AuthenticationErrorException();
        }

        return EventTemplateDTOMapper.INSTANCE.getDTO(eventTemplate);
    }
}
