package fhv.team11.project.ems.commons.database;

public interface IDTOEntityBiMapper<E, D> {
    E getEntity(D dto);
    D getDTO(E entity);
}
