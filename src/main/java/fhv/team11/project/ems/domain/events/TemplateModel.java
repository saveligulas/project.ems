package fhv.team11.project.ems.domain.events;

import fhv.team11.project.ems.commons.domain.DomainFieldCollectionValidationException;
import fhv.team11.project.ems.commons.domain.DomainFieldValidationException;
import fhv.team11.project.ems.commons.domain.DomainInstantiationException;
import fhv.team11.project.ems.commons.domain.IDomainObject;
import fhv.team11.project.ems.domain.adress.AddressModel;

import fhv.team11.project.ems.domain.commons.*;
import fhv.team11.project.ems.events.repo.EventCategory;

import lombok.Setter;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;


public class TemplateModel implements IDomainObject {
    @Nullable
    private transient HashMap<String, String> fieldErrors;

    @Nullable
    private Long id;

    private String name;
    @Setter
    private EventCategory category;
    private double price;
    private Integer maxParticipants;
    private Integer minParticipants;
    @Setter
    private AddressModel address;

    public TemplateModel(Long id, String name, EventCategory category, double price, int maxParticipants, int minParticipants, AddressModel address) throws DomainFieldCollectionValidationException, DomainFieldValidationException {
        fieldErrors = new HashMap<>();

        setId(id);
        setName(name);
        setCategory(category);
        setPrice(price);
        setParticipants(maxParticipants, minParticipants);
        setAddress(address);
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

    public void setParticipants(int maxParticipants, int minParticipants) throws DomainFieldValidationException, DomainFieldCollectionValidationException{


        if (!ParticipantsValidation.isValid(minParticipants)) {
            String fieldName = "MinParticipants";
            String errorMessage = "Min participants number must be greater than 0 and smaller then 1000";

            if (this.isInstantiated()) {
                throw new DomainFieldValidationException(fieldName, errorMessage);
            } else {
                fieldErrors.put(fieldName, errorMessage);
            }
        }

        if (!ParticipantsValidation.isValid(maxParticipants)) {
            String fieldName = "MaxParticipants";
            String errorMessage = "Max participants number must be greater than 0 and smaller then 1000";

            if (this.isInstantiated()) {
                throw new DomainFieldValidationException(fieldName, errorMessage);
            } else {
                fieldErrors.put(fieldName, errorMessage);
            }
        }

        if (maxParticipants < minParticipants) {
            ArrayList<String> fieldNames = new ArrayList<>();
            fieldNames.add("MinParticipants");
            fieldNames.add("MaxParticipants");
            String errorMessage = "Min participants number must be smaller then Max participants number";

            if (this.isInstantiated()) {
                throw new DomainFieldCollectionValidationException(fieldNames, errorMessage);
            } else {
                fieldErrors.put(fieldNames.get(0), errorMessage);
                fieldErrors.put(fieldNames.get(1), errorMessage);
            }
        }

        this.minParticipants = minParticipants;
        this.maxParticipants = maxParticipants;
    }

    public void setPrice(double price) throws DomainFieldValidationException {

        if (!PriceValidation.isValid(maxParticipants)) {
            String fieldName = "Price";
            String errorMessage = "Price must be greater than 0";

            if (this.isInstantiated()) {
                throw new DomainFieldValidationException(fieldName, errorMessage);
            } else {
                fieldErrors.put(fieldName, errorMessage);
            }
        }

        this.price = price;
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
