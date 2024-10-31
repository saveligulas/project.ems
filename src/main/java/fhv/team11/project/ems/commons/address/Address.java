package fhv.team11.project.ems.commons.address;

import fhv.team11.project.ems.events.repo.Blueprint;
import fhv.team11.project.ems.events.repo.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Address {
    @Id
    private Long id;

    @OneToOne(mappedBy = "address")
    private Blueprint blueprint;
}
