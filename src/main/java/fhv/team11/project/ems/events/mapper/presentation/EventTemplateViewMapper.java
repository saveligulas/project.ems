package fhv.team11.project.ems.events.mapper.presentation;

import fhv.team11.project.ems.commons.address.AddressDTOMapper;
import fhv.team11.project.ems.commons.address.AddressDTOMapperNew;
import fhv.team11.project.ems.commons.error.BackEndError;
import fhv.team11.project.ems.commons.mapper.IDomainPresentationMapper;
import fhv.team11.project.ems.domain.events.EventTemplate;
import fhv.team11.project.ems.events.transfer.EventTemplateView;
import org.jspecify.annotations.Nullable;

public class EventTemplateViewMapper implements IDomainPresentationMapper<EventTemplateView, EventTemplate> {
    public static final EventTemplateViewMapper INSTANCE = new EventTemplateViewMapper();

    private EventTemplateViewMapper() {
    }

    @Override
    public EventTemplateView getView(@Nullable EventTemplate domain) {
        EventTemplateView eventTemplateView = new EventTemplateView();

        if (domain == null) {
            throw new BackEndError("Event without template was attempted to be viewed");
        }

        eventTemplateView.setId(domain.getId());
        eventTemplateView.setName(domain.getName());
        eventTemplateView.setCategory(domain.getCategory());
        eventTemplateView.setPrice(domain.getPrice());
        eventTemplateView.setMaxParticipants(domain.getMaxParticipants());
        eventTemplateView.setMinParticipants(domain.getMinParticipants());
        eventTemplateView.setAddress(AddressDTOMapperNew.INSTANCE.getView(domain.getAddress()));
        return eventTemplateView;
    }
}
