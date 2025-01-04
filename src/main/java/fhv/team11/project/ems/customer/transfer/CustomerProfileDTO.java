package fhv.team11.project.ems.customer.transfer;

import fhv.team11.project.ems.commons.address.AddressDTO;
import fhv.team11.project.ems.commons.validation.model.IModelAttribute;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.thymeleaf.model.IModel;

@Data
public class CustomerProfileDTO implements IModelAttribute {

    private Long id;

    private String firstName;

    private String lastName;

    private AddressDTO address;

    private String phoneNumber;

    private Integer secret;

}
