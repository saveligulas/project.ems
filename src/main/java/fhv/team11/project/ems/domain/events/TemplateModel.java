package fhv.team11.project.ems.domain.events;


import fhv.team11.project.ems.domain.adress.AddressModel;

import fhv.team11.project.ems.domain.commons.*;
import fhv.team11.project.ems.domain.commons.exception.DomainFieldValidationException;
import fhv.team11.project.ems.events.repo.EventCategory;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;

@Getter
public class TemplateModel implements IDomainObject {
    private final DomainObjectConstructorHelper constructorHelper;

    @Nullable
    private Long id;

    private String name;
    @Setter
    private EventCategory category;
    private double price;
    private Integer maxParticipants;
    private Integer minParticipants;
    private Integer overbookedPlaces;
    @Setter
    private AddressModel address;

    public TemplateModel(Long id, String name, EventCategory category, double price, int maxParticipants, int minParticipants, AddressModel address, Integer overbookedPlaces) throws DomainFieldValidationException {
        this.constructorHelper = new DomainObjectConstructorHelper();


        setId(id);
        setName(name);
        setCategory(category);
        setPrice(price);
        setOverbookedPlaces(overbookedPlaces);
        setParticipants(maxParticipants, minParticipants);
        setAddress(address);

        this.constructorHelper.finish();
    }

    public void setOverbookedPlaces(Integer overbookedPlaces) throws DomainFieldValidationException {

        String fieldName;
        String errorMessage;

        if (!NameValidator.isValid(name)) {
            fieldName = "OverBookedPlaces";
            errorMessage = "OverBookedPLaces can not be negative";

            handleError(fieldName,errorMessage,constructorHelper);
        }

        this.overbookedPlaces = overbookedPlaces;
    }

    private void setName(String name) throws DomainFieldValidationException {
        String fieldName;
        String errorMessage;

        if (!NameValidator.isValid(name)) {
            fieldName = "Name";
            errorMessage = "";

            handleError(fieldName,errorMessage,constructorHelper);
        }

    }

    public void setParticipants(int maxParticipants, int minParticipants) throws DomainFieldValidationException{
        String fieldName;
        String errorMessage;

        if (!ParticipantsValidation.isValid(minParticipants)) {
            fieldName = "MinParticipants";
            errorMessage = "Min participants number must be greater than 0 and smaller then 1000";

            handleError(fieldName,errorMessage,constructorHelper);
        }

        if (!ParticipantsValidation.isValid(maxParticipants)) {
            fieldName = "MaxParticipants";
            errorMessage = "Max participants number must be greater than 0 and smaller then 1000";

            handleError(fieldName,errorMessage,constructorHelper);
        }

        if (maxParticipants < minParticipants) {
            ArrayList<String> fieldNames = new ArrayList<>();
            fieldNames.add("MinParticipants");
            fieldNames.add("MaxParticipants");
            errorMessage = "Min participants number must be smaller then Max participants number";

            handleError(fieldNames.get(0),errorMessage,constructorHelper);
            handleError(fieldNames.get(1),errorMessage,constructorHelper);
        }

        this.minParticipants = minParticipants;
        this.maxParticipants = maxParticipants;
    }

    public void setPrice(double price) throws DomainFieldValidationException {

        if (!PriceValidation.isValid(maxParticipants)) {
            String fieldName = "Price";
            String errorMessage = "Price must be greater than 0";

            handleError(fieldName,errorMessage,constructorHelper);
        }

        this.price = price;
    }

    public void setId(Long id) throws DomainFieldValidationException {

        if (!IndexValidator.isValid(id)) {
            String fieldName = "Id";
            String errorMessage = "Id must be positive";

            handleError(fieldName,errorMessage,constructorHelper);
        }

        this.id = id;
    }
}
