package fhv.team11.project.ems.events.mapper;

public class ActiveEventEntityMapper {

    public static final ActiveEventEntityMapper INSTANCE = new ActiveEventEntityMapper();

    /*public ActiveEvent toEntity(ActiveEventModel activeEventModel, EventTemplate eventTemplate) {
        ActiveEvent activeEvent = new ActiveEvent();
        activeEvent.setEventTemplate(eventTemplate);

        activeEvent.setEventDate(activeEventModel.getActiveEventDates().stream()
                .map(ActiveEventDateEntityMapper.INSTANCE::toEntity)
                .collect(Collectors.toCollection(TreeSet::new)));
        activeEvent.setId(activeEventModel.getId());

        return activeEvent;

    }

    public ActiveEventModel toModel(ActiveEvent activeEvent, Appointment appointment) throws DomainValidationException {
        return new ActiveEventModel(activeEvent.getId(), activeEvent.getEventDate().stream()
                        .map(ActiveEventDateEntityMapper.INSTANCE::toModel)
                        .collect(Collectors.toCollection(TreeSet::new)), ScheduleEntityMapper.INSTANCE.toModel(appointment));
    }*/
}
