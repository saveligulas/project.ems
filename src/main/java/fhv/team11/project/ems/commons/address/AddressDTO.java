package fhv.team11.project.ems.commons.address;

import jakarta.validation.constraints.*;
import lombok.Data;

//TODO: Logic to service layer
@Data
public class AddressDTO {
    private String country;
    private String region;
    private String city;
    private Integer zip;
    private String street;
    private String houseNumber;
    private String optionalText;
}
