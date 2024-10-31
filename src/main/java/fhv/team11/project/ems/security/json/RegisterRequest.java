package fhv.team11.project.ems.security.json;

import fhv.team11.project.ems.commons.validation.FieldsMatch;
import fhv.team11.project.ems.commons.validation.order.FirstValidation;
import fhv.team11.project.ems.commons.validation.order.SecondValidation;
import fhv.team11.project.ems.commons.validation.order.ThirdValidation;
import fhv.team11.project.ems.security.controller.validation.PasswordConstraint;
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
public class RegisterRequest {
    @NotBlank(message = "Please enter an email address", groups = FirstValidation.class)
    @Email(message = "Please enter a valid email address", groups = FirstValidation.class)
    private String email;

    @NotBlank(message = "Please enter a password", groups = SecondValidation.class)
    @PasswordConstraint(groups = ThirdValidation.class)
    private String password;

    @NotBlank(message = "Please confirm the password", groups = ThirdValidation.class)
    private String confirmPassword;
}
