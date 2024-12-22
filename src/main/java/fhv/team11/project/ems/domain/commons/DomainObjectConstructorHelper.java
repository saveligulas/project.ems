package fhv.team11.project.ems.domain.commons;

import fhv.team11.project.ems.domain.commons.exception.DomainInstantiationException;

import java.util.ArrayList;
import java.util.List;

public class DomainObjectConstructorHelper {
    private boolean isBeingConstructed;
    final List<DomainFieldError> domainFieldErrors;
    final List<DomainFieldGroupError> domainFieldGroupErrors;
    final List<DomainStateError> domainStateErrors;

    public DomainObjectConstructorHelper() {
        isBeingConstructed = true;
        this.domainFieldErrors = new ArrayList<>();
        this.domainFieldGroupErrors = new ArrayList<>();
        this.domainStateErrors = new ArrayList<>();
    }

    public void add(DomainFieldError domainFieldError) {
        domainFieldErrors.add(domainFieldError);
    }

    public void add(DomainFieldGroupError domainFieldGroupError) {
        domainFieldGroupErrors.add(domainFieldGroupError);
    }

    public void add(DomainStateError domainStateError) {
        domainStateErrors.add(domainStateError);
    }

    public boolean isBeingConstructed() {
        return isBeingConstructed;
    }

    public void finish() throws DomainInstantiationException {
        isBeingConstructed = false;
        if (!domainFieldErrors.isEmpty() || !domainFieldGroupErrors.isEmpty() || !domainStateErrors.isEmpty()) {
            DomainObjectConstructorHelperExceptionBuilder.buildException(this);
        }
    }
}
