package fhv.team11.project.ems.commons.address;

import jakarta.validation.constraints.*;
import lombok.Data;

//TODO: Logic to service layer
@Data
public class AddressDTO {
    @NotBlank(message = "Bitte geben sie ein Land ein")
    private String country;

    @NotBlank(message = "Bitte geben sie ein Bundesland ein")
    private String region;

    @NotBlank(message = "Bitte geben sie ein Stadt ein")
    private String city;

    @NotNull(message = "Bitte geben sie eine Postleitzahl an")
    @Max(value = 99999L,message = "Die Postleitzahl darf nicht höher als 99999 sein")
    @Min(value = 1000L, message = "Die Postleitzahl darf nicht kleiner als 1000 sein")
    private Integer zip;

    @NotBlank(message = "Bitte geben sie ein Strasse ein")
    private String street;

    @NotBlank(message = "Bitte geben sie eine Hausnummer an")
    private String houseNumber;

    @Size(max = 150, message = "Der Text darf maximal 150 Zeichen lang sein")
    private String optionalText;
}
