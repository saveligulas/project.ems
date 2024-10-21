package fhv.team11.project.ems.commons.database;

public interface IRepository<E, ID> {
    E save(E entity);
    E update(E entity);
    E findById(ID id);
    E deleteById(ID id);
}
