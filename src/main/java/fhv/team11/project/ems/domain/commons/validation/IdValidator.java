package fhv.team11.project.ems.domain.commons.validation;

import fhv.team11.project.ems.domain.commons.exception.DomainFieldException;
import fhv.team11.project.ems.domain.commons.exception.error.DomainObjectConstructorHelper;

public class IdValidator {
    public static boolean isValid(Long id) {
        return isValid(id, new DomainObjectConstructorHelper());
    }

    public static boolean isValid(Long id, DomainObjectConstructorHelper constructorHelper) {
        if (id == null && !constructorHelper.isBeingConstructed()) {
            return false;
        }

        if (id != null && id < 0) {
            return false;
        }

        return true;
    }

    public static Long validate(Long id, DomainObjectConstructorHelper constructorHelper) throws DomainFieldException {
        if (!isValid(id, constructorHelper)) {
            throw new DomainFieldException("id", "ID is invalid");
        }
        return id;
    }
}
