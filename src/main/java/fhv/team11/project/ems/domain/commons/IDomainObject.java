package fhv.team11.project.ems.domain.commons;

import fhv.team11.project.ems.domain.commons.exception.DomainFieldException;

public interface IDomainObject {
    default void handleError(String fieldName, String errorMessage, DomainObjectConstructorHelper constructorHelper) throws DomainFieldException {
        if (!constructorHelper.isBeingConstructed()) {
            throw new DomainFieldException(fieldName, errorMessage);
        }
        constructorHelper.add(new DomainFieldError(fieldName, errorMessage));
    }
}
