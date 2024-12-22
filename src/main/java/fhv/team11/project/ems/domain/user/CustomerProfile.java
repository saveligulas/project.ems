package fhv.team11.project.ems.domain.user;

import fhv.team11.project.ems.domain.commons.DomainObjectConstructorHelper;
import fhv.team11.project.ems.domain.commons.IdValidator;
import fhv.team11.project.ems.domain.commons.exception.DomainFieldException;

public class CustomerProfile {
    private final DomainObjectConstructorHelper constructorHelper;

    private Long id;
    private String firstName;
    private String lastName;
    private TempAddress address;
    private String phoneNumber;
    private Integer secret;

    public CustomerProfile(Long id,
                           Integer secret,
                           String phoneNumber,
                           TempAddress address,
                           String lastName,
                           String firstName
    ) throws DomainFieldException {
        this.constructorHelper = new DomainObjectConstructorHelper();

        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAddress(address);
        this.setPhoneNumber(phoneNumber);
        this.setSecret(secret);

        constructorHelper.finish();
    }

    public void setId(Long id) throws DomainFieldException {
        this.id = IdValidator.validate(id, constructorHelper);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(TempAddress address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSecret(Integer secret) {
        this.secret = secret;
    }
}
