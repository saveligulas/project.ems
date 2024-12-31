package fhv.team11.project.ems.events.mapper.presentation;

import fhv.team11.project.ems.commons.address.AddressDTOMapper;
import fhv.team11.project.ems.commons.address.AddressDTOMapperNew;
import fhv.team11.project.ems.commons.mapper.IPresentationDomainMapper;
import fhv.team11.project.ems.commons.validation.ValidationExceptionDecorator;
import fhv.team11.project.ems.domain.adress.Address;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.events.EventTemplate;
import fhv.team11.project.ems.events.transfer.EventTemplateDTO;

public class EventTemplateDTOMapper implements IPresentationDomainMapper<EventTemplateDTO, EventTemplate> {
    public static final EventTemplateDTOMapper INSTANCE = new EventTemplateDTOMapper();

    private EventTemplateDTOMapper() {}

    @Override
    public EventTemplate getDomain(EventTemplateDTO presentationObject) throws DomainValidationException {
        Address address = null;
        try {
            address = AddressDTOMapperNew.INSTANCE.getDomain(presentationObject.getAddress());
        } catch (DomainValidationException e) {
            ValidationExceptionDecorator.encapsulateFieldErrorsTo("address", e);
        }


        return new EventTemplate(
                null,
                presentationObject.getName(),
                presentationObject.getCategory(),
                presentationObject.getPrice(),
                presentationObject.getMaxParticipants(),
                presentationObject.getMinParticipants(),
                address,
                2
        );
    }
}
