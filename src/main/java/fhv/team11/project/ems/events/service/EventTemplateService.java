package fhv.team11.project.ems.events.service;

import fhv.team11.project.ems.commons.error.EntityNotFoundException;
import fhv.team11.project.ems.commons.validation.domain.DomainValidatorFactory;
import fhv.team11.project.ems.events.error.EventTemplateDTOValidationException;
import fhv.team11.project.ems.events.repo.EventTemplate;
import fhv.team11.project.ems.events.repo.EventTemplateRepository;

import fhv.team11.project.ems.events.transfer.EventTemplateDTO;
import fhv.team11.project.ems.events.transfer.EventTemplateListDTO;
import fhv.team11.project.ems.security.error.SecuredEndpointAccessException;
import fhv.team11.project.ems.security.jwt.JwtSecurityContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Slf4j
@Validated
public class EventTemplateService {

    private final EventTemplateRepository eventTemplateRepository;
    private final DomainValidatorFactory domainValidatorFactory;

    @Autowired
    public EventTemplateService(EventTemplateRepository eventTemplateRepository, DomainValidatorFactory domainValidatorFactory) {
        this.eventTemplateRepository = eventTemplateRepository;
        this.domainValidatorFactory = domainValidatorFactory;
    }

    private boolean hasAccess(EventTemplate eventTemplate) {
        return eventTemplate.getUserEntity().getId().equals(JwtSecurityContextHolder.getUser().getId());
    }

    public void createNewTemplate(EventTemplateDTO eventTemplateDTO) {
        BindingResult bindingResult = domainValidatorFactory.getValidator(EventTemplateDTO.class).validate(eventTemplateDTO);
        if (bindingResult.hasErrors()) {
            throw new EventTemplateDTOValidationException(bindingResult);
        }

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
            throw new SecuredEndpointAccessException();
        }

        return EventTemplateDTOMapper.INSTANCE.getDTO(eventTemplate);
    }
}
