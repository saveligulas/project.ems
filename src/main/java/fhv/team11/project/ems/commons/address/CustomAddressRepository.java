package fhv.team11.project.ems.commons.address;

import java.util.Optional;

public interface CustomAddressRepository {
    Optional<Address> findById();
    void persist(Address address);
}
