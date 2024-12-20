package fhv.team11.project.ems.domain.adress;

import fhv.team11.project.ems.commons.domain.DomainFieldValidationException;
import fhv.team11.project.ems.commons.domain.DomainInstantiationException;
import fhv.team11.project.ems.commons.domain.IDomainObject;
import fhv.team11.project.ems.domain.commons.*;
import fhv.team11.project.ems.events.repo.EventTemplate;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import javax.annotation.Nullable;
import java.util.HashMap;

public class AddressModel implements IDomainObject {

    @Nullable
    private transient HashMap<String, String> fieldErrors;

    @Nullable
    private Long id;

    private String country;
    private String region;
    private String city;
    private Integer zip;
    private String street;
    private String houseNumber;
    @Nullable
    private String optionalText;

    public AddressModel(Long id, String country, String region, String city, Integer zip, String street, String houseNumber, String optionalText) throws DomainFieldValidationException {
        setId(id);
        setCountry(country);
        setRegion(region);
        setCity(city);
        setZip(zip);
        this.street = street;
        this.houseNumber = houseNumber;
        this.optionalText = optionalText;
    }

    public void setRegion(String region) throws DomainFieldValidationException {
        String fieldName = "Region";
        String errorMessage = "Region can not be empty";


        if(!CountryValidator.isValid(country)){
            if(isInstantiated()){
                throw new DomainFieldValidationException(fieldName,errorMessage);
            }
            else {
                fieldErrors.put(fieldName, errorMessage);
            }
        }

        this.region = region;
    }

    public void setStreet(String street) throws DomainFieldValidationException {
        String fieldName = "Street";
        String errorMessage = "Street can not be empty";


        if(!StreetValidator.isValid(country)){
            if(isInstantiated()){
                throw new DomainFieldValidationException(fieldName,errorMessage);
            }
            else {
                fieldErrors.put(fieldName, errorMessage);
            }
        }
        this.street = street;
    }

    public void setHouseNumber(String houseNumber) throws DomainFieldValidationException {
        String fieldName = "HouseNumber";
        String errorMessage = "HouseNumber can not be empty";


        if(!HouseNumberValidator.isValid(country)){
            if(isInstantiated()){
                throw new DomainFieldValidationException(fieldName,errorMessage);
            }
            else {
                fieldErrors.put(fieldName, errorMessage);
            }
        }
        this.houseNumber = houseNumber;
    }

    public void setOptionalText(@Nullable String optionalText) {
        this.optionalText = optionalText;
    }

    public void setCountry(String country) throws DomainFieldValidationException{
        String fieldName = "Country";
        String errorMessage = "Country can not be empty";


        if(!CountryValidator.isValid(country)){
            if(isInstantiated()){
                throw new DomainFieldValidationException(fieldName,errorMessage);
            }
            else {
                fieldErrors.put(fieldName, errorMessage);
            }
        }

        this.country = country;
    }

    public void setCity(String city) throws DomainFieldValidationException{
        String fieldName = "City";
        String errorMessage = "City can not be empty";


        if(!CityValidator.isValid(city)){
            if(isInstantiated()){
                throw new DomainFieldValidationException(fieldName,errorMessage);
            }
            else {
                fieldErrors.put(fieldName, errorMessage);
            }
        }

        this.city = city;
    }

    public void setZip(Integer zip) throws DomainFieldValidationException{
        String fieldName = "zip";
        String errorMessage = "Zip must be 4 or 5 letters long and not empty";


        if(!ZipValidation.isValid(zip)){
            if(isInstantiated()){
                throw new DomainFieldValidationException(fieldName,errorMessage);
            }
            else {
                fieldErrors.put(fieldName, errorMessage);
            }
        }

        this.zip = zip;
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
