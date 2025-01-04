package fhv.team11.project.ems.events.mapper.presentation;

import fhv.team11.project.ems.commons.mapper.IDomainPresentationMapper;
import fhv.team11.project.ems.commons.mapper.IPresentationDomainMapper;
import fhv.team11.project.ems.domain.events.Event;
import fhv.team11.project.ems.events.transfer.ActiveEventView;
import fhv.team11.project.ems.events.transfer.EventTemplateView;

public class ActiveEventViewDTOMapper implements IDomainPresentationMapper<ActiveEventView, Event> {

    public static final ActiveEventViewDTOMapper INSTANCE = new ActiveEventViewDTOMapper();

    private ActiveEventViewDTOMapper() {
    }
    @Override
    public ActiveEventView getView(Event domain) {
        ActiveEventView view = new ActiveEventView();

        view.setId(domain.getId());
        view.setEventTemplateView(EventTemplateViewMapper.INSTANCE.getView(domain.getEventTemplate()));
        view.setEventDates(domain.getEventDates().stream().map(EventDateDTOMapper.INSTANCE::getView).toList());

        return view;
    }
}
