package fhv.team11.project.ems.security.transfer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @NotBlank(message = "Please enter an email address")
    //@Email(message = "Please enter a valid email address") - Removed this for future domain validation if User is Admin and does not use an email
    private String email;

    @NotBlank(message = "Please enter a password")
    private String password;
}
