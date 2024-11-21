package fhv.team11.project.ems.commons.database;

public interface IEntityDTOMapper<E, D> {
    D getDTO(E e);
}
