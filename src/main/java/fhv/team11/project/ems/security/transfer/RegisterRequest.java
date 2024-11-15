package fhv.team11.project.ems.security.transfer;

import fhv.team11.project.ems.commons.validation.model.IModelAttribute;
import fhv.team11.project.ems.commons.validation.constraints.FieldsMatch;
import fhv.team11.project.ems.commons.validation.order.FirstValidation;
import fhv.team11.project.ems.commons.validation.order.SecondValidation;
import fhv.team11.project.ems.commons.validation.order.ThirdValidation;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldsMatch(fieldOne = "password", fieldTwo = "confirmPassword", message = "Passwords do not match")
@GroupSequence({FirstValidation.class, SecondValidation.class, ThirdValidation.class, RegisterRequest.class})
public class RegisterRequest implements IModelAttribute {
    @NotBlank(message = "Please enter an email address", groups = FirstValidation.class)
    @Email(message = "Please enter a valid email address", groups = FirstValidation.class)
    private String email;

    @NotBlank(message = "Please enter a password", groups = SecondValidation.class)
    private String password;

    @NotBlank(message = "Please confirm the password", groups = ThirdValidation.class)
    private String confirmPassword;
}
