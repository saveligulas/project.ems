package fhv.team11.project.ems.commons.domain;

public interface ISimpleDomainDatabaseMapper<D, E> {
    E toNotPersistedEntity(D domain);
    D toDomain(E entity);
}
