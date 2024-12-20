package fhv.team11.project.ems.domain.events;

import fhv.team11.project.ems.commons.domain.DomainFieldValidationException;
import fhv.team11.project.ems.commons.domain.DomainInstantiationException;
import fhv.team11.project.ems.commons.domain.IDomainObject;
import fhv.team11.project.ems.domain.commons.DateValidator;
import fhv.team11.project.ems.domain.commons.IndexValidator;
import fhv.team11.project.ems.domain.commons.NameValidator;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.util.HashMap;

public class ActiveEventDateModel implements IDomainObject {
    @Nullable
    private transient HashMap<String, String> fieldErrors;

    @Nullable
    private Long id;

    private LocalDate date;
    @Nullable
    private String  name;

    public ActiveEventDateModel(Long id, LocalDate date, String name) throws DomainFieldValidationException {
        fieldErrors = new HashMap<>();

        setId(id);
        setDate(date);
        setName(name);
    }

    public void setDate(LocalDate date) throws DomainFieldValidationException {

        if (!DateValidator.isValid(date)) {
            String fieldName = "date";
            String errorMessage = "date must be in the future";

            if (this.isInstantiated()) {
                throw new DomainFieldValidationException(fieldName, errorMessage);
            } else {
                fieldErrors.put(fieldName, errorMessage);
            }
        }

        this.date = date;
    }

    public void setId(Long id) throws DomainFieldValidationException {

        if (!IndexValidator.isValid(id)) {
            String fieldName = "Id";
            String errorMessage = "Id must be positive";

            if (this.isInstantiated()) {
                throw new DomainFieldValidationException(fieldName, errorMessage);
            } else {
                fieldErrors.put(fieldName, errorMessage);
            }
        }

        this.id = id;
    }

    private void setName(String name) throws DomainFieldValidationException {

        if (!NameValidator.isValid(name)) {
            String fieldName = "Name";
            String errorMessage = "";

            if (this.isInstantiated()) {
                throw new DomainFieldValidationException(fieldName, errorMessage);
            } else {
                fieldErrors.put(fieldName, errorMessage);
            }
        }

    }

    @Override
    public void checkFieldErrors() throws DomainInstantiationException {
        if (fieldErrors != null && !fieldErrors.isEmpty()) {
            throw new DomainInstantiationException(fieldErrors);
        }
        fieldErrors = null;
    }

    @Override
    public boolean isInstantiated() {
        return fieldErrors == null;
    }
}
