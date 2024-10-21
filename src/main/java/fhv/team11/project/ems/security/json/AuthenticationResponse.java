package fhv.team11.project.ems.security.json;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private String authToken;
    private String message;

    public AuthenticationResponse(String message) {
        this.authToken = null;
        this.message = message;
    }
}
