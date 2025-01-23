package fhv.team11.project.ems.domain.user;

import fhv.team11.project.ems.domain.adress.Address;
import fhv.team11.project.ems.domain.commons.exception.error.DomainObjectConstructorHelper;
import fhv.team11.project.ems.domain.commons.interfaces.IDomainObject;
import fhv.team11.project.ems.domain.commons.validation.IdValidator;
import fhv.team11.project.ems.domain.commons.exception.DomainFieldException;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import lombok.Getter;

@Getter
public class CustomerProfile implements IDomainObject {
    private final DomainObjectConstructorHelper constructorHelper;

    private Long id;
    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private Integer secret;

    public CustomerProfile(Long id,
                           Integer secret,
                           String phoneNumber,
                           Address address,
                           String lastName,
                           String firstName
    ) throws DomainValidationException {
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

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSecret(Integer secret) throws DomainFieldException {
        validateNotNull("secret", secret, constructorHelper);
        this.secret = secret;
    }
}
