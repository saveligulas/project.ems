package fhv.team11.project.ems.domain.commons;

import fhv.team11.project.ems.domain.commons.exception.DomainFieldValidationException;

public interface IDomainObject {
    default void handleError(String fieldName, String errorMessage, DomainObjectConstructorHelper constructorHelper) throws DomainFieldValidationException {
        if (!constructorHelper.isBeingConstructed()) {
            throw new DomainFieldValidationException(fieldName, errorMessage);
        }
        constructorHelper.add(new DomainFieldError(fieldName, errorMessage));
    }
}
