package fhv.team11.project.ems.domain.commons;

import java.util.List;

public class DomainFieldGroupError {
    final List<DomainFieldError> domainFieldErrors;

    public DomainFieldGroupError(List<DomainFieldError> domainFieldErrors) {
        this.domainFieldErrors = domainFieldErrors;
    }
}
