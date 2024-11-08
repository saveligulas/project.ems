package fhv.team11.project.ems.commons.address;

import fhv.team11.project.ems.events.repo.EventTemplate;
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
    private EventTemplate eventTemplate;

    private String country;
    private String region;
    private String city;
    private Integer zip;
    private String street;
    private String houseNumber;
    private String optionalText;
}
