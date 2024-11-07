package fhv.team11.project.ems.events.repo;

import fhv.team11.project.ems.commons.database.IRepository;
import fhv.team11.project.ems.commons.error.DatabaseException;

import java.util.List;

public interface BlueprintRepositoryQuery extends IRepository<Blueprint, Long> {

    List<Blueprint> listNumberOfBlueprints(int num);
}
