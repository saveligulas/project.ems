package fhv.team11.project.ems.commons.domain;

import fhv.team11.project.ems.commons.validation.model.IModelAttribute;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import lombok.Getter;

@Getter
public class DomainToBindingResultException extends RuntimeException {
    private final DomainValidationException validationException;
    private final IModelAttribute modelAttribute;
    private final String redirectEndpoint;

    public DomainToBindingResultException(DomainValidationException validationException, IModelAttribute modelAttribute, String redirectEndpoint) {
        this.validationException = validationException;
        this.modelAttribute = modelAttribute;
        this.redirectEndpoint = redirectEndpoint;
    }
}
