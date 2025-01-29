package fhv.team11.project.ems.domain.commons.interfaces;

import fhv.team11.project.ems.domain.adress.Address;

public class SimpleRepresentRealWorldEntity implements IRepresentRealWorldEntity {
    private Long id;
    private Address address;
    private String surname;
    private String name;

    public SimpleRepresentRealWorldEntity(Long id, Address address, String surname, String name) {
        this.id = id;
        this.address = address;
        this.surname = surname;
        this.name = name;
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public String getName() {
        return name;
    }
}
