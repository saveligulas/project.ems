package fhv.team11.project.ems.events.transfer;

import fhv.team11.project.ems.commons.address.AddressDTO;
import fhv.team11.project.ems.events.repo.Category;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class BlueprintDTO {
    @NotBlank
    private String name;
    private Category category;

    @DecimalMax("1000.0")
    @DecimalMin("0.0")
    private double price;

    @Max(1000L)
    @Min(0L)
    private int maxParticipants;

    @Max(1000L)
    @Min(0L)
    private int minParticipants;

    @NotNull
    private AddressDTO address;
}
