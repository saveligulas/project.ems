package fhv.team11.project.ems.commons.mapper;

import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;

public interface IPresentationDomainMapper<P, D> {
    D getDomain(P presentationObject) throws DomainValidationException;
}
