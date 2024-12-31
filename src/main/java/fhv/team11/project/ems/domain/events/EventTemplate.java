package fhv.team11.project.ems.domain.events;


import fhv.team11.project.ems.domain.adress.Address;

import fhv.team11.project.ems.domain.commons.*;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.events.repo.EventCategory;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import java.util.ArrayList;

@Getter
public class EventTemplate implements IDomainObject {
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
    private Address address;

    public EventTemplate(Long id, String name, EventCategory category, double price, int maxParticipants, int minParticipants, Address address, Integer overbookedPlaces) throws DomainValidationException {
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

    public void setOverbookedPlaces(Integer overbookedPlaces) throws DomainValidationException {

        String fieldName;
        String errorMessage;

        if (!NameValidator.isValid(name)) {
            fieldName = "OverBookedPlaces";
            errorMessage = "OverBookedPLaces can not be negative";

            handleError(fieldName,errorMessage,constructorHelper);
        }

        this.overbookedPlaces = overbookedPlaces;
    }

    private void setName(String name) throws DomainValidationException {
        String fieldName;
        String errorMessage;

        if (!StringValidator.isValid(name, 5)) {
            fieldName = "name";
            errorMessage = "Name has to be minimum 5 characters";

            handleError(fieldName,errorMessage,constructorHelper);
        }
        this.name = name;
    }

    public void setParticipants(int maxParticipants, int minParticipants) throws DomainValidationException{
        String fieldName;
        String errorMessage;

        if (!ParticipantsValidation.isValid(minParticipants)) {
            fieldName = "minParticipants";
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
            fieldNames.add("minParticipants");
            fieldNames.add("maxParticipants");
            errorMessage = "Min participants number must be smaller then Max participants number";

            handleError(fieldNames.get(0),errorMessage,constructorHelper);
            handleError(fieldNames.get(1),errorMessage,constructorHelper);
        }

        this.minParticipants = minParticipants;
        this.maxParticipants = maxParticipants;
    }

    public void setPrice(double price) throws DomainValidationException {

        if (!PriceValidation.isValid(maxParticipants)) {
            String fieldName = "price";
            String errorMessage = "Price must be greater than 0";

            handleError(fieldName,errorMessage,constructorHelper);
        }

        this.price = price;
    }

    public void setId(Long id) throws DomainValidationException {
        validateId(id, constructorHelper);

        this.id = id;
    }
}
