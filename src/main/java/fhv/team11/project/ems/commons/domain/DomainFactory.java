package fhv.team11.project.ems.commons.domain;

import fhv.team11.project.ems.commons.controller.error.handler.HandleRedirectException;
import fhv.team11.project.ems.commons.validation.domain.DomainValidatorFactory;
import fhv.team11.project.ems.commons.validation.domain.handler.HandleBindingResultException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public abstract class DomainFactory<E, D, M> implements HandleBindingResultException, HandleRedirectException {

    protected final DomainValidatorFactory domainValidatorFactory;

    public DomainFactory(DomainValidatorFactory domainValidatorFactory) {
        this.domainValidatorFactory = domainValidatorFactory;
    }

    public abstract D getDomain();

    public abstract D getDomainFromEntity(E entity);

    public abstract D getDomainFromModel(M model);

    public D getDomainFromDTO(M dto) {
        return getDomainFromModel(dto);
    }
}
