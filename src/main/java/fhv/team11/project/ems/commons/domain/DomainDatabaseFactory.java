package fhv.team11.project.ems.commons.domain;

import fhv.team11.project.ems.commons.controller.error.handler.HandleRedirectException;
import fhv.team11.project.ems.commons.validation.domain.handler.HandleBindingResultException;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import org.springframework.stereotype.Component;

@Component
public abstract class DomainDatabaseFactory<D, ID> implements HandleBindingResultException, HandleRedirectException {

    public abstract D getDomainById(ID id) throws DomainValidationException;

    public abstract ID persist(D domainObject) throws DomainValidationException;

}


