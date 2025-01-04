package fhv.team11.project.ems.events.service;

import fhv.team11.project.ems.commons.error.EntityNotFoundException;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.events.EventTemplateDomainDatabaseFactory;
import fhv.team11.project.ems.events.mapper.presentation.EventTemplateDTOMapper;
import fhv.team11.project.ems.events.repo.EventTemplateEntity;
import fhv.team11.project.ems.events.repo.EventTemplateEntityRepository;

import fhv.team11.project.ems.events.transfer.EventTemplateDTO;
import fhv.team11.project.ems.events.transfer.EventTemplateListDTO;
import fhv.team11.project.ems.security.error.SecuredEndpointAccessException;
import fhv.team11.project.ems.security.jwt.JwtSecurityContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Slf4j
@Validated
public class EventTemplateService {

    private final EventTemplateEntityRepository eventTemplateEntityRepository;
    private final EventTemplateDomainDatabaseFactory domainDatabaseFactory;
    private final EventTemplateDomainDatabaseFactory eventTemplateDomainDatabaseFactory;

    @Autowired
    public EventTemplateService(EventTemplateEntityRepository eventTemplateEntityRepository, EventTemplateDomainDatabaseFactory domainDatabaseFactory, EventTemplateDomainDatabaseFactory eventTemplateDomainDatabaseFactory) {
        this.eventTemplateEntityRepository = eventTemplateEntityRepository;
        this.domainDatabaseFactory = domainDatabaseFactory;
        this.eventTemplateDomainDatabaseFactory = eventTemplateDomainDatabaseFactory;
    }

    private boolean hasAccess(EventTemplateEntity eventTemplateEntity) {
        return eventTemplateEntity.getUser().getId().equals(JwtSecurityContextHolder.getUser().getId());
    }

    public void createNewTemplate(EventTemplateDTO eventTemplateDTO) throws DomainValidationException {
        eventTemplateDomainDatabaseFactory.persist(EventTemplateDTOMapper.INSTANCE.getDomain(eventTemplateDTO));
    }

    public List<EventTemplateListDTO> getListOfTemplates(int pageNumber, int pageSize) {
        return eventTemplateEntityRepository.getEventTemplatesForPageNumber(pageNumber, pageSize, JwtSecurityContextHolder.getUser().getId())
                .stream()
                .map(EventTemplateListDTODatabaseMapper.INSTANCE::getView)
                .toList();
    }
    public EventTemplateListDTO getTemplateListByID(Long templateId) {
        return eventTemplateEntityRepository.findById(templateId)
                .map(EventTemplateListDTODatabaseMapper.INSTANCE::getView)
                .orElse(null);
    }
    public EventTemplateDTO getTemplateByName(String name) {
        //TODO: fix
        // return EventTemplateDTOMapper.INSTANCE.getDomain(eventTemplateRepository.getEventTemplateByName(name));
        return null;
    }

    public EventTemplateDTO getTemplateById(Long templateId) {
        EventTemplateEntity eventTemplateEntity = eventTemplateEntityRepository.findById(templateId)
                .orElseThrow(() -> new EntityNotFoundException(EventTemplateEntity.class, templateId));

        if (!hasAccess(eventTemplateEntity)) {
            throw new SecuredEndpointAccessException();
        }

        return null;
    }
}
