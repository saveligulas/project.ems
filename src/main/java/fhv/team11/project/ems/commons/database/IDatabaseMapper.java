package fhv.team11.project.ems.commons.database;

import fhv.team11.project.ems.commons.error.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IDatabaseMapper<E, ID> {
    /**
     * Saves the given entity.
     *
     * @param entity the entity to save
     * @return the saved entity
     */
    E save(E entity);

    /**
     * Finds an entity by its ID.
     *
     * @param id the ID of the entity
     * @return an Optional containing the found entity or empty if not found
     */
    Optional<E> findById(ID id) throws EntityNotFoundException;

    /**
     * Finds all entities.
     *
     * @return a list of all entities
     */
    List<E> findAll();

    /**
     * Updates the given entity.
     *
     * @param entity the entity to update
     * @return the updated entity
     */
    E update(E entity);

    /**
     * Deletes an entity by its ID.
     *
     * @param id the ID of the entity to delete
     */
    void deleteById(ID id);
}
