package fhv.team11.project.ems.events.mapper.presentation;

import fhv.team11.project.ems.commons.mapper.IDomainPresentationMapper;
import fhv.team11.project.ems.domain.events.Event;
import fhv.team11.project.ems.events.transfer.ActiveEventView;

public class ActiveEventViewMapper implements IDomainPresentationMapper<ActiveEventView, Event> {

    public static final ActiveEventViewMapper INSTANCE = new ActiveEventViewMapper();

    private ActiveEventViewMapper() {
    }
    @Override
    public ActiveEventView getView(Event domain) {
        ActiveEventView view = new ActiveEventView();

        view.setEventTemplateView(EventTemplateViewMapper.INSTANCE.getView(domain.getEventTemplate()));
        view.setEventDates(domain.getEventDates().stream().map(EventDateDTOMapper.INSTANCE::getView).toList());
        view.setBookedPlaces(domain.getBookedPlaces());
        return view;
    }
}
