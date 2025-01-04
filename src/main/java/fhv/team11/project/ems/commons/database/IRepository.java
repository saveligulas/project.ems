package fhv.team11.project.ems.commons.database;

import fhv.team11.project.ems.commons.error.DatabaseException;

import java.util.Optional;

public interface IRepository<E, ID> {
    E persist(E entity);
    E update(E entity);
}
