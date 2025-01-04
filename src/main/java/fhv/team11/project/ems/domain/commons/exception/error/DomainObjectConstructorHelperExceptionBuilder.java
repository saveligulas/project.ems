package fhv.team11.project.ems.domain.commons.exception.error;

import fhv.team11.project.ems.domain.commons.exception.DomainInstantiationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DomainObjectConstructorHelperExceptionBuilder {
    static void buildException(DomainObjectConstructorHelper domainObjectConstructorHelper) throws DomainInstantiationException {
        Map<String, String> fieldErrorMessages = new HashMap<>();
        List<String> stateErrorMessages = new ArrayList<>();

        for (DomainFieldError domainFieldError : domainObjectConstructorHelper.domainFieldErrors) {
            fieldErrorMessages.put(domainFieldError.fieldName, domainFieldError.errorMessage);
        }

        for (DomainFieldGroupError domainFieldGroupError : domainObjectConstructorHelper.domainFieldGroupErrors) {
            for (DomainFieldError domainFieldError : domainFieldGroupError.domainFieldErrors) {
                addIfFieldPresent(fieldErrorMessages, domainFieldError.fieldName, domainFieldError.errorMessage);
            }
        }

        for (DomainStateError domainStateError : domainObjectConstructorHelper.domainStateErrors) {
            stateErrorMessages.add(domainStateError.errorMessage);
        }

        throw new DomainInstantiationException(fieldErrorMessages, stateErrorMessages);
    }

    private static void addIfFieldPresent(Map<String, String> fieldErrorMessages, String fieldName, String errorMessage) {
        if (fieldErrorMessages.containsKey(fieldName)) {
            if (!errorMessage.isBlank()) {
                fieldErrorMessages.put(fieldName, fieldErrorMessages.get(fieldName) + " | " + errorMessage);
            }
        } else {
            fieldErrorMessages.put(fieldName, errorMessage);
        }
    }
}
