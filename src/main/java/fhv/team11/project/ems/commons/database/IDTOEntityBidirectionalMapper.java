package fhv.team11.project.ems.commons.database;

public interface IDTOEntityBidirectionalMapper<E, D> {
    E getEntity(D dto);
    D getDTO(E entity);
}
