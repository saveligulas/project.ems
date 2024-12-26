package fhv.team11.project.ems.events.mapper;

import fhv.team11.project.ems.domain.commons.exception.DomainFieldValidationException;
import fhv.team11.project.ems.domain.events.ActiveEventModel;
import fhv.team11.project.ems.events.repo.*;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ActiveEventEntityMapper {

    public static final ActiveEventEntityMapper INSTANCE = new ActiveEventEntityMapper();

    public ActiveEvent toEntity(ActiveEventModel activeEventModel, EventTemplate eventTemplate) {
        ActiveEvent activeEvent = new ActiveEvent();
        activeEvent.setEventTemplate(eventTemplate);

        activeEvent.setEventDate(activeEventModel.getActiveEventDates().stream()
                .map(ActiveEventDateEntityMapper.INSTANCE::toEntity)
                .collect(Collectors.toCollection(TreeSet::new)));
        activeEvent.setId(activeEventModel.getId());

        return activeEvent;

    }

    public ActiveEventModel toModel(ActiveEvent activeEvent, Appointment appointment) throws DomainFieldValidationException {
        return new ActiveEventModel(activeEvent.getId(), activeEvent.getEventDate().stream()
                        .map(ActiveEventDateEntityMapper.INSTANCE::toModel)
                        .collect(Collectors.toCollection(TreeSet::new)), ScheduleEntityMapper.INSTANCE.toModel(appointment));
    }
}
