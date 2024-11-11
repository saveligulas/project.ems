package fhv.team11.project.ems.commons.database;

public interface IDTOMapper<E, D> {
    E getEntity(D dto);
    D getDTO(E entity);
}
