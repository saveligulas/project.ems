package fhv.team11.project.ems.events.mapper;

import fhv.team11.project.ems.domain.commons.exception.DomainFieldValidationException;
import fhv.team11.project.ems.domain.events.ActiveEventDateModel;
import fhv.team11.project.ems.events.repo.EventDate;

public class ActiveEventDateEntityMapper {

    public static final ActiveEventDateEntityMapper INSTANCE = new ActiveEventDateEntityMapper();

    public EventDate toEntity(ActiveEventDateModel activeEventDateModel) {
        EventDate eventDate = new EventDate();

        eventDate.setId(activeEventDateModel.getId());
        eventDate.setDate(activeEventDateModel.getDate());
        eventDate.setName(activeEventDateModel.getName());

        return eventDate;
    }

    public ActiveEventDateModel toModel(EventDate eventDate) throws DomainFieldValidationException {
        return new ActiveEventDateModel(eventDate.getId(),eventDate.getDate(),eventDate.getName());
    }
}
