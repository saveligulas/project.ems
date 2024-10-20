package fhv.team11.project.ems.commons.mapper;

/**
 * A generic interface for mapping between database entities and domain models.
 *
 * @param <E> the type of the entity representing the database table.
 * @param <M> the type of the model used in the services.
 */
public interface IMapper<E, M> {

    /**
     *
     * @param e Entity
     * @return Model for use in Services
     */
    M toDomainModel(E e);

    /**
     *
     * @param m Model
     * @return Entity representing database table
     * @Warning This method can return Entities with missing fields
     */
    E toEntity(M m);

    /**
     *
     * @param e Entity
     * @param m Model
     * @return the updated entity. Is the same instance as the provided Entity in the parameters.
     */
    E updateEntity(E e, M m);
}
