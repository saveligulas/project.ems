package fhv.team11.project.ems.domain.commons.exception;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DomainInstantiationException extends DomainFieldException {
    private final List<String> stateErrorMessages;

    public DomainInstantiationException(Map<String, String> fieldErrors, List<String> stateErrorMessages) {
        super(fieldErrors);
        this.stateErrorMessages = Collections.unmodifiableList(stateErrorMessages);
    }
}
