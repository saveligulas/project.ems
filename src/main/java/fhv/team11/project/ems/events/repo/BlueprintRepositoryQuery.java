package fhv.team11.project.ems.events.repo;

import fhv.team11.project.ems.commons.error.DatabaseException;

public interface BlueprintRepositoryQuery {
    void persist(Blueprint blueprint) throws DatabaseException;
}
