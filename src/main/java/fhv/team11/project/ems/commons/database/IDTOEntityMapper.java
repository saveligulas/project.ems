package fhv.team11.project.ems.commons.database;

public interface IDTOEntityMapper<E, D> {
    E getEntity(D d);
}
