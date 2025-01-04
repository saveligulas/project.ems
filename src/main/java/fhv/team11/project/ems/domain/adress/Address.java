package fhv.team11.project.ems.domain.adress;


import fhv.team11.project.ems.domain.commons.Validator;
import fhv.team11.project.ems.domain.commons.exception.DomainFieldException;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.commons.exception.error.DomainObjectConstructorHelper;
import fhv.team11.project.ems.domain.commons.interfaces.IDomainObject;
import fhv.team11.project.ems.domain.commons.validation.IdValidator;
import fhv.team11.project.ems.domain.commons.validation.StringValidator;
import lombok.Getter;
import org.jspecify.annotations.Nullable;


@Getter
public class Address implements IDomainObject {

    private final DomainObjectConstructorHelper constructorHelper;

    @Nullable
    private Long id;

    private String country;
    @Nullable
    private String region;
    private String city;
    private Integer zip;
    private String street;
    private String houseNumber;
    @Nullable
    private String optionalText;

    public Address(Long id, String country, String region, String city, Integer zip, String street, String houseNumber, String optionalText) throws DomainValidationException {
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

    public void setRegion(String region) throws DomainFieldException {
        String fieldName = "region";

        if (!Validator.isBlank(region)) {
            region = null;
        }

        this.region = region;
    }

    public void setStreet(String street) throws DomainFieldException {
        String fieldName = "street";
        String errorMessage = "Street can not be empty";


        if(!StringValidator.isValid(country)){
            handleError(fieldName,errorMessage,constructorHelper);
        }
        this.street = street;
    }

    public void setHouseNumber(String houseNumber) throws DomainFieldException {
        String fieldName = "houseNumber";
        String errorMessage = "House Number can not be empty";


        if(!StringValidator.isValid(country)){
            handleError(fieldName,errorMessage,constructorHelper);
        }
        this.houseNumber = houseNumber;
    }

    public void setOptionalText(@Nullable String optionalText) {
        this.optionalText = optionalText;
    }

    public void setCountry(String country) throws DomainFieldException{
        String fieldName = "country";
        String errorMessage = "Country can not be empty";


        if(!StringValidator.isValid(country)){
            handleError(fieldName,errorMessage,constructorHelper);
        }

        this.country = country;
    }

    public void setCity(String city) throws DomainFieldException{
        String fieldName = "city";
        String errorMessage = "City can not be empty";


        if(!StringValidator.isValid(city)){
            handleError(fieldName,errorMessage,constructorHelper);
        }

        this.city = city;
    }

    public void setZip(Integer zip) throws DomainFieldException{
        String fieldName = "zip";
        String errorMessage = "Zip must be 4 or 5 digits long and not empty";


        if(zip == null || !StringValidator.isValid(zip.toString(), 4, 5)){
            handleError(fieldName,errorMessage,constructorHelper);
        }

        this.zip = zip;
    }

    public void setId(Long id) throws DomainFieldException {

        if (!IdValidator.isValid(id)) {
            String fieldName = "id";
            String errorMessage = "Id must be valid";

            handleError(fieldName,errorMessage,constructorHelper);
        }

        this.id = id;
    }


}
