package fhv.team11.project.ems.domain.commons.exception;

import java.util.List;

public class DomainStateException extends DomainValidationException {
    public DomainStateException(List<String> errorMessages) {
        super(errorMessages);
    }
}
