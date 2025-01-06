package fhv.team11.project.ems.events.mapper.presentation;

import fhv.team11.project.ems.commons.database.IPresentationDatabaseMapper;
import fhv.team11.project.ems.events.repo.ActiveEvent;
import fhv.team11.project.ems.events.transfer.ActiveEventView;
import fhv.team11.project.ems.events.transfer.ActiveEventViewShallow;

public class ActiveEventViewShallowDatabaseMapper implements IPresentationDatabaseMapper<ActiveEventViewShallow, ActiveEvent> {
    public static final ActiveEventViewShallowDatabaseMapper INSTANCE = new ActiveEventViewShallowDatabaseMapper();
    
    private ActiveEventViewShallowDatabaseMapper() {
    }
    
    @Override
    public ActiveEventViewShallow getView(ActiveEvent entity) {
        ActiveEventViewShallow view = new ActiveEventViewShallow();
        view.setEventDates(entity.getEventDates().stream()
                .map(EventDateDTODatabaseMapper.INSTANCE::getView)
                .toList());
        return view;
    }
}
