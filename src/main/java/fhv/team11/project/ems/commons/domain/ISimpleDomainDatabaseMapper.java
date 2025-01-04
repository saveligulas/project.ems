package fhv.team11.project.ems.commons.domain;

import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;

public interface ISimpleDomainDatabaseMapper<D, E> {
    E toEntity(D domain);
    D toDomain(E entity) throws DomainValidationException;
}
