package fhv.team11.project.ems.commons.address;

import fhv.team11.project.ems.events.repo.EventTemplate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "address")
    private EventTemplate eventTemplate;

    @NotNull
    private String country;

    @NotNull
    private String region;

    @NotNull
    private String city;

    @NotNull
    private Integer zip;

    @NotNull
    private String street;

    @NotNull
    private String houseNumber;
    private String optionalText;
}
