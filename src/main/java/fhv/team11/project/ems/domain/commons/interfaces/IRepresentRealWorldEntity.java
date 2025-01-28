package fhv.team11.project.ems.domain.commons.interfaces;

import fhv.team11.project.ems.domain.adress.Address;

public interface IRepresentRealWorldEntity {
    Long getId();
    Address getAddress();
     default String getLegalName() {
         return getSurname() + " " + getName();
     }
    String getSurname();
    String getName();
}
