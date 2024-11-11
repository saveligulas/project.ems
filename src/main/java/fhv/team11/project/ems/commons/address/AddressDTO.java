package fhv.team11.project.ems.commons.address;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressDTO {
    @NotBlank
    private String country;

    @NotBlank
    private String region;

    @NotBlank
    private String city;

    @NotNull
    @Max(99999L)
    @Min(1000L)
    private Integer zip;

    @NotBlank
    private String street;

    @NotBlank
    private String houseNumber;

    @NotBlank
    private String optionalText;
}
