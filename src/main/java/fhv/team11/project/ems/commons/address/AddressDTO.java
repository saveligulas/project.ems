package fhv.team11.project.ems.commons.address;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddressDTO {
    @NotBlank
    private String country;

    @NotBlank
    private String region;

    @NotBlank
    private String city;

    @NotBlank
    private String zip;

    @NotBlank
    private String street;

    @NotBlank
    private String houseNumber;

    @NotBlank
    private String optionalText;
}
