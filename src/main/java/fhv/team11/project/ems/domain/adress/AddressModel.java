package fhv.team11.project.ems.domain.adress;


import fhv.team11.project.ems.domain.commons.*;
import fhv.team11.project.ems.domain.commons.exception.DomainFieldValidationException;
import lombok.Getter;

import javax.annotation.Nullable;

@Getter
public class AddressModel implements IDomainObject {

    private final DomainObjectConstructorHelper constructorHelper;

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
        this.constructorHelper = new DomainObjectConstructorHelper();
        setId(id);
        setCountry(country);
        setRegion(region);
        setCity(city);
        setZip(zip);
        setStreet(street);
        setHouseNumber(houseNumber);
        setOptionalText(optionalText);

        this.constructorHelper.finish();
    }

    public void setRegion(String region) throws DomainFieldValidationException {
        String fieldName = "Region";
        String errorMessage = "Region can not be empty";


        if(!CountryValidator.isValid(country)){
            handleError(fieldName,errorMessage,constructorHelper);
        }

        this.region = region;
    }

    public void setStreet(String street) throws DomainFieldValidationException {
        String fieldName = "Street";
        String errorMessage = "Street can not be empty";


        if(!StreetValidator.isValid(country)){
            handleError(fieldName,errorMessage,constructorHelper);
        }
        this.street = street;
    }

    public void setHouseNumber(String houseNumber) throws DomainFieldValidationException {
        String fieldName = "HouseNumber";
        String errorMessage = "HouseNumber can not be empty";


        if(!HouseNumberValidator.isValid(country)){
            handleError(fieldName,errorMessage,constructorHelper);
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
            handleError(fieldName,errorMessage,constructorHelper);
        }

        this.country = country;
    }

    public void setCity(String city) throws DomainFieldValidationException{
        String fieldName = "City";
        String errorMessage = "City can not be empty";


        if(!CityValidator.isValid(city)){
            handleError(fieldName,errorMessage,constructorHelper);
        }

        this.city = city;
    }

    public void setZip(Integer zip) throws DomainFieldValidationException{
        String fieldName = "zip";
        String errorMessage = "Zip must be 4 or 5 letters long and not empty";


        if(!ZipValidation.isValid(zip)){
            handleError(fieldName,errorMessage,constructorHelper);
        }

        this.zip = zip;
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
