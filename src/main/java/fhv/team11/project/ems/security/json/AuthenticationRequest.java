package fhv.team11.project.ems.security.json;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    @NotBlank(message = "Please enter an email address")
    private String email;
    @NotBlank(message = "Please enter a password")
    private String password;
}
