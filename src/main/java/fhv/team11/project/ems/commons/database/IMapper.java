package fhv.team11.project.ems.commons.database;

/**
 * A generic interface for mapping between database entities and domain models.
 *
 * @param <E> the type of the entity representing the database table.
 * @param <M> the type of the model used in the services.
 */
public interface IMapper<E, M> {

    /**
     *
     * @param entity Entity
     * @return Model for use in Services
     */
    M toDomainModel(E entity);

    /**
     *
     * @param model Model
     * @return Entity representing database table
     * @Warning This method can return Entities with missing fields
     */
    E toEntity(M model);

    /**
     *
     * @param entity Entity
     * @param model Model
     * @return the updated entity. Is the same instance as the provided Entity in the parameters
     */
    E updateEntity(E entity, M model);
}
