package fhv.team11.project.ems.domain.commons.interfaces;

import fhv.team11.project.ems.domain.commons.Validator;
import fhv.team11.project.ems.domain.commons.exception.error.DomainFieldError;
import fhv.team11.project.ems.domain.commons.exception.error.DomainObjectConstructorHelper;
import fhv.team11.project.ems.domain.commons.exception.error.DomainStateError;
import fhv.team11.project.ems.domain.commons.validation.IdValidator;
import fhv.team11.project.ems.domain.commons.exception.DomainFieldException;
import fhv.team11.project.ems.domain.commons.exception.DomainStateException;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;

import java.util.List;

public interface IDomainObject {
    default void handleError(String fieldName, String errorMessage, DomainObjectConstructorHelper constructorHelper) throws DomainFieldException {
        if (!constructorHelper.isBeingConstructed()) {
            throw new DomainFieldException(fieldName, errorMessage);
        }
        constructorHelper.add(new DomainFieldError(fieldName, errorMessage));
    }

    default void handleError(String errorMessage, DomainObjectConstructorHelper constructorHelper) throws DomainValidationException {
        if (!constructorHelper.isBeingConstructed()) {
            throw new DomainStateException(List.of(errorMessage));
        }
        constructorHelper.add(new DomainStateError(errorMessage));
    }

    default void validateId(Long id, DomainObjectConstructorHelper constructorHelper) throws DomainFieldException {
        if (!IdValidator.isValid(id)) {
            handleError("id", constructIdMessage(), constructorHelper);
        }
    }

    default void validateNotNullOrBlank(String fieldName, Object value, DomainObjectConstructorHelper constructorHelper) throws DomainFieldException {
        if (Validator.isNull(value)) {

        }
    }

    default String constructIdMessage() {
        return "ID is not valid";
    }

    default String constructInvalid() {
        return "Is not a valid input";
    }

    default String constructMessageInvalid(String fieldName) {
        return fieldName + " is invalid";
    }

    default String constructMissing() {
        return "Field is required";
    }

    default String constructMessageMissing(String fieldName) {
        if (!fieldName.isBlank()) {
            return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1) + " is required";
        }
        return "Is required";
    }
}
