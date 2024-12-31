package fhv.team11.project.ems.events.repo;

import fhv.team11.project.ems.commons.database.IRepository;

public interface EventDateRepositoryQuery extends IRepository<EventDateEntity,Long>{
    EventDateEntity plsPersist(EventDateEntity entity, Schedule schedule, ActiveEvent activeEvent);
}
