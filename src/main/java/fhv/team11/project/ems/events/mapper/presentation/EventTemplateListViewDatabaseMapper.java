package fhv.team11.project.ems.events.mapper.presentation;

import fhv.team11.project.ems.commons.database.IPresentationDatabaseMapper;
import fhv.team11.project.ems.events.repo.EventTemplateEntity;
import fhv.team11.project.ems.events.transfer.EventTemplateListView;

public class EventTemplateListViewDatabaseMapper implements IPresentationDatabaseMapper<EventTemplateListView, EventTemplateEntity> {

    public static final EventTemplateListViewDatabaseMapper INSTANCE = new EventTemplateListViewDatabaseMapper();

    private EventTemplateListViewDatabaseMapper() {
    }

    @Override
    public EventTemplateListView getView(EventTemplateEntity entity) {
        EventTemplateListView view = new EventTemplateListView();
        view.setId(entity.getId());
        view.setName(entity.getName());
        view.setCategory(entity.getCategory());
        view.setPlannedEventCount(entity.getActiveEvents().size());
        return view;
    }
}
