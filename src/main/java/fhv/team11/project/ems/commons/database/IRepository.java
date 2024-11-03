package fhv.team11.project.ems.commons.database;

import java.util.Optional;

public interface IRepository<E, ID> {
    E persist(E entity);
    E update(E entity);
    Optional<E> findById(ID id);
    void deleteById(ID id);
    String constructSqlStatement(String action, String clause);
}
