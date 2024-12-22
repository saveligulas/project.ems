package fhv.team11.project.ems.commons.domain;

public abstract class ExpandedDomainDatabaseFactory<D, ID, E> extends DomainDatabaseFactory<D, ID> {
    public abstract D toDomain(E entity);
}
