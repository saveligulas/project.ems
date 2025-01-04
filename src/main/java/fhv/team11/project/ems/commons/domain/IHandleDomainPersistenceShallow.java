package fhv.team11.project.ems.commons.domain;

import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;

public interface IHandleDomainPersistenceShallow<D> {
    void persist(D domainObject) throws DomainValidationException;
}
