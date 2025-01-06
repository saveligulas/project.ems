package fhv.team11.project.ems.commons.database;

public interface IPresentationDatabaseMapper<P, E> {
    P getView(E entity);
}
