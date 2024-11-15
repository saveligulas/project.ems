package fhv.team11.project.ems.commons.database;

public interface IDTOMapper<E, D> {
    D getDTO(E e);
}
