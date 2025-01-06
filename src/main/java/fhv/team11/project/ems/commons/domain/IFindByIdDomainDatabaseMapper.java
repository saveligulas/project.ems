package fhv.team11.project.ems.commons.domain;

import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;

public interface IFindByIdDomainDatabaseMapper<D, ID> {
   D getDomainById(ID id) throws DomainValidationException;
}
