package fhv.team11.project.ems.events.repo;

import fhv.team11.project.ems.commons.database.IRepository;

import java.util.List;

public interface EventTemplateRepositoryQuery extends IRepository<EventTemplate, Long> {

    List<EventTemplate> listNumberOfBlueprints(int num);
}
