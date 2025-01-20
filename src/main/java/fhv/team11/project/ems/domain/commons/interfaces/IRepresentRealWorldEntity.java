package fhv.team11.project.ems.domain.commons.interfaces;

import fhv.team11.project.ems.domain.adress.Address;

public interface IRepresentRealWorldEntity {
    Address getAddress();
    String getLegalName();
    String getSurname();
    String getName();
}
