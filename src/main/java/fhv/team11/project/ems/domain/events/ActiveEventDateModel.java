package fhv.team11.project.ems.domain.events;


import fhv.team11.project.ems.domain.commons.*;
import fhv.team11.project.ems.domain.commons.exception.DomainFieldValidationException;
import fhv.team11.project.ems.domain.commons.exception.DomainInstantiationException;
import lombok.Getter;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.util.HashMap;

@Getter
public class ActiveEventDateModel implements IDomainObject {
    private final DomainObjectConstructorHelper constructorHelper;

    @Nullable
    private Long id;

    private LocalDate date;
    @Nullable
    private String  name;

    public ActiveEventDateModel(Long id, LocalDate date, String name) throws DomainFieldValidationException {
        this.constructorHelper = new DomainObjectConstructorHelper();

        setId(id);
        setDate(date);
        setName(name);
        this.constructorHelper.finish();
    }

    public void setDate(LocalDate date) throws DomainFieldValidationException {
        String fieldName = "date";
        String errorMessage = "date must be in the future";

        if (!DateValidator.isValid(date)) {
            handleError(fieldName,errorMessage,constructorHelper);
        }

        this.date = date;
    }

    public void setId(Long id) throws DomainFieldValidationException {
        String fieldName = "Id";
        String errorMessage = "Id must be positive";

        if (!IndexValidator.isValid(id)) {
            handleError(fieldName,errorMessage,constructorHelper);
        }

        this.id = id;
    }

    private void setName(String name) throws DomainFieldValidationException {
        String fieldName = "Name";
        String errorMessage = "";

        if (!NameValidator.isValid(name)) {
            handleError(fieldName,errorMessage,constructorHelper);
        }

    }

}
