package fhv.team11.project.ems.events.mapper.presentation;

import fhv.team11.project.ems.commons.mapper.IDomainPresentationMapper;
import fhv.team11.project.ems.domain.commons.exception.DomainStateException;
import fhv.team11.project.ems.domain.events.Event;
import fhv.team11.project.ems.events.transfer.ActiveEventListView;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class ActiveEventListViewMapper implements IDomainPresentationMapper<ActiveEventListView, Event> {
    public static final ActiveEventListViewMapper INSTANCE = new ActiveEventListViewMapper();

    private ActiveEventListViewMapper() {
    }

    @Override
    public ActiveEventListView getView(Event domain) {
        ActiveEventListView view = new ActiveEventListView();
        view.setId(domain.getId());
        try {
            view.setStart(domain.getFirstEventDate());
            view.setEnd(domain.getLastEventDate());
        } catch (DomainStateException e) {
            log.error("Active event has no event dates set");
            log.error(Arrays.stream(e.getStackTrace()).toList().toString());
        }
        return view;
    }
}
