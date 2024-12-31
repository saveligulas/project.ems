package fhv.team11.project.ems.events.mapper;

import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.events.EventTemplate;
import fhv.team11.project.ems.events.repo.EventTemplateEntity;

import java.math.BigDecimal;

public class TemplateEntityMapper {

    public static final TemplateEntityMapper INSTANCE = new TemplateEntityMapper();

    public EventTemplateEntity toEntity(EventTemplate eventTemplate) {
        EventTemplateEntity eventTemplateEntity = new EventTemplateEntity();
        eventTemplateEntity.setId(eventTemplate.getId());
        eventTemplateEntity.setName(eventTemplate.getName());
        eventTemplateEntity.setCategory(eventTemplate.getCategory());
        eventTemplateEntity.setAddressEntity(AddressEntityMapper.INSTANCE.toEntity(eventTemplate.getAddress()));
        eventTemplateEntity.setMaxParticipants(eventTemplate.getMaxParticipants());
        eventTemplateEntity.setMinParticipants(eventTemplate.getMinParticipants());
        eventTemplateEntity.setPrice(BigDecimal.valueOf(eventTemplate.getPrice()));
        eventTemplateEntity.setOverbookingPlaces(eventTemplate.getOverbookedPlaces());

        return eventTemplateEntity;
    }

    public EventTemplate toModel(EventTemplateEntity eventTemplateEntity) throws DomainValidationException {
        return new EventTemplate(eventTemplateEntity.getId(), eventTemplateEntity.getName(), eventTemplateEntity.getCategory(),Double.valueOf(String.valueOf(eventTemplateEntity.getPrice())), eventTemplateEntity.getMaxParticipants(), eventTemplateEntity.getMinParticipants(),
                AddressEntityMapper.INSTANCE.toModel(eventTemplateEntity.getAddressEntity()), eventTemplateEntity.getOverbookingPlaces());
    }

}
