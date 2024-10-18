package fhv.team11.project.ems.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import fhv.team11.project.ems.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtTokenService {
    private final Algorithm algorithm;

    @Autowired
    public JwtTokenService(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public String generateAuthenticationToken(UserModel user) {
        String token;
        try {
            Instant expiration = Instant.now().plus(1, ChronoUnit.DAYS);
            token = JWT.create()
                    .withIssuer("authentication-token")
                    .withSubject(user.getUsername())
                    .withExpiresAt(Date.from(expiration))
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            token = null;
        }
        return token;
    }
}
