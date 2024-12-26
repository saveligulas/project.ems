package fhv.team11.project.ems.events.mapper;

import fhv.team11.project.ems.domain.commons.exception.DomainFieldValidationException;
import fhv.team11.project.ems.domain.events.TemplateModel;
import fhv.team11.project.ems.events.repo.EventTemplate;
import fhv.team11.project.ems.events.transfer.EventTemplateDTO;

import java.math.BigDecimal;

public class TemplateEntityMapper {

    public static final TemplateEntityMapper INSTANCE = new TemplateEntityMapper();

    public EventTemplate toEntity(TemplateModel eventTemplate) {
        EventTemplate eventTemplateEntity = new EventTemplate();
        eventTemplateEntity.setId(eventTemplate.getId());
        eventTemplateEntity.setName(eventTemplate.getName());
        eventTemplateEntity.setCategory(eventTemplate.getCategory());
        eventTemplateEntity.setAddress(AddressEntityMapper.INSTANCE.toEntity(eventTemplate.getAddress()));
        eventTemplateEntity.setMaxParticipants(eventTemplate.getMaxParticipants());
        eventTemplateEntity.setMinParticipants(eventTemplate.getMinParticipants());
        eventTemplateEntity.setPrice(BigDecimal.valueOf(eventTemplate.getPrice()));
        eventTemplateEntity.setOverbookingPlaces(eventTemplate.getOverbookedPlaces());

        return eventTemplateEntity;
    }

    public TemplateModel toModel(EventTemplate eventTemplate) throws DomainFieldValidationException {
        return new TemplateModel(eventTemplate.getId(), eventTemplate.getName(), eventTemplate.getCategory(),Double.valueOf(String.valueOf(eventTemplate.getPrice())), eventTemplate.getMaxParticipants(), eventTemplate.getMinParticipants(),
                AddressEntityMapper.INSTANCE.toModel(eventTemplate.getAddress()), eventTemplate.getOverbookingPlaces());
    }

}
