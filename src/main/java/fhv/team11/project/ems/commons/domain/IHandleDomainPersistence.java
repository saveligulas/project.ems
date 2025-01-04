package fhv.team11.project.ems.commons.domain;

import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;

public interface IHandleDomainPersistence<D> {
    D persist(D domainObject) throws DomainValidationException;
}
