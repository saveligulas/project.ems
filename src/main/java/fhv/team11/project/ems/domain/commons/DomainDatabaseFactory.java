package fhv.team11.project.ems.domain.commons;

import fhv.team11.project.ems.commons.controller.error.handler.HandleRedirectException;
import fhv.team11.project.ems.commons.validation.domain.DomainValidatorFactory;
import fhv.team11.project.ems.commons.validation.domain.handler.HandleBindingResultException;
import org.springframework.stereotype.Component;

@Component
public abstract class DomainDatabaseFactory<D, ID> implements HandleBindingResultException, HandleRedirectException {

    public abstract D toDomain(ID id);

    public abstract void persist(D domainObject);

}


