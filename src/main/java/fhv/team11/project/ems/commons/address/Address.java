package fhv.team11.project.ems.commons.address;

import fhv.team11.project.ems.events.repo.Blueprint;
import fhv.team11.project.ems.events.repo.Category;
import jakarta.persistence.*;
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
    private Blueprint blueprint;

    private String country;
    private String region;
    private String city;
    private String zip;
    private String street;
    private String number;
    private String optionalText;
}
