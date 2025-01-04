package fhv.team11.project.ems.events;

import fhv.team11.project.ems.commons.address.AddressDomainDatabaseFactory;
import fhv.team11.project.ems.commons.domain.DomainDatabaseFactory;
import fhv.team11.project.ems.commons.domain.IFindByIdDomainDatabaseMapper;
import fhv.team11.project.ems.commons.domain.IHandleDomainPersistence;
import fhv.team11.project.ems.commons.domain.ISimpleDomainDatabaseMapper;
import fhv.team11.project.ems.commons.error.EntityNotFoundException;
import fhv.team11.project.ems.domain.adress.Address;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.events.EventTemplate;
import fhv.team11.project.ems.events.repo.EventTemplateEntity;
import fhv.team11.project.ems.events.repo.EventTemplateEntityRepository;
import fhv.team11.project.ems.security.jwt.JwtSecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class EventTemplateDomainDatabaseFactory extends DomainDatabaseFactory implements ISimpleDomainDatabaseMapper<EventTemplate, EventTemplateEntity>, IHandleDomainPersistence<EventTemplate>, IFindByIdDomainDatabaseMapper<EventTemplate, Long> {

    private final AddressDomainDatabaseFactory addressDomainDatabaseFactory;
    private final EventTemplateEntityRepository eventTemplateEntityRepository;

    @Autowired
    public EventTemplateDomainDatabaseFactory(AddressDomainDatabaseFactory addressDomainDatabaseFactory, EventTemplateEntityRepository eventTemplateEntityRepository) {
        this.addressDomainDatabaseFactory = addressDomainDatabaseFactory;
        this.eventTemplateEntityRepository = eventTemplateEntityRepository;
    }

    @Override
    public EventTemplateEntity toEntity(EventTemplate domain) {
        EventTemplateEntity entity = new EventTemplateEntity();

        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setCategory(domain.getCategory());
        entity.setPrice(BigDecimal.valueOf(domain.getPrice()));
        entity.setMinParticipants(domain.getMinParticipants());
        entity.setMaxParticipants(domain.getMaxParticipants());
        entity.setOverbookingPlaces(domain.getOverbookedPlaces());
        entity.setAddress(addressDomainDatabaseFactory.toEntity(domain.getAddress()));

        return entity;
    }

    @Override
    public EventTemplate toDomain(EventTemplateEntity entity) throws DomainValidationException {
        Address address = addressDomainDatabaseFactory.toDomain(entity.getAddress());

        return new EventTemplate(
                entity.getId(),
                entity.getName(),
                entity.getCategory(),
                entity.getPrice().doubleValue(),
                entity.getMaxParticipants(),
                entity.getMinParticipants(),
                address,
                entity.getOverbookingPlaces()
        );
    }

    @Override
    public EventTemplate persist(EventTemplate domainObject) throws DomainValidationException {
        EventTemplateEntity entity = toEntity(domainObject);
        entity.setUser(JwtSecurityContextHolder.getUser());
        return toDomain(eventTemplateEntityRepository.save(entity));
    }

    @Override
    public EventTemplate getDomainById(Long templateId) throws DomainValidationException {
        return toDomain(eventTemplateEntityRepository.findById(templateId)
                .orElseThrow(() -> new EntityNotFoundException(EventTemplateEntity.class, templateId)));
    }
}
