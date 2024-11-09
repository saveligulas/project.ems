package fhv.team11.project.ems.commons.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddressConfig {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressConfig(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

}
