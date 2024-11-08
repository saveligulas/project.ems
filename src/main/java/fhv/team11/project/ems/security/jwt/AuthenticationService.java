package fhv.team11.project.ems.security.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import fhv.team11.project.ems.security.error.UserNotFoundException;
import fhv.team11.project.ems.user.repo.Role;
import fhv.team11.project.ems.user.repo.UserJDBCRepository;
import fhv.team11.project.ems.security.error.*;
import fhv.team11.project.ems.security.json.AuthenticationRequest;
import fhv.team11.project.ems.security.json.AuthenticationResponse;
import fhv.team11.project.ems.user.repo.entity.UserJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {
    private final UserJDBCRepository userJDBCRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final Algorithm algorithm;
    private final JwtTokenService jwtTokenService;

    @Autowired
    public AuthenticationService(UserJDBCRepository userJDBCRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, Algorithm algorithm, JwtTokenService jwtTokenService) {
        this.userJDBCRepository = userJDBCRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.algorithm = algorithm;
        this.jwtTokenService = jwtTokenService;

    }

    public AuthenticationResponse register(String email, String password) {

        if (userJDBCRepository.findByEmail(email).isPresent()) {
            throw new RegistrationEmailAlreadyRegisteredException();
        }

        UserJDBC user = new UserJDBC();
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(List.of(Role.CUSTOMER, Role.ADMIN, Role.EMPLOYEE));

        userJDBCRepository.save(user);

        return new AuthenticationResponse("User registration was successful");
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            // Authenticate user with email and password
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            // Find the user by email
            UserJDBC user = userJDBCRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UserNotFoundException(request.getEmail()));

            // Generate JWT token for authenticated user
            String authToken = jwtTokenService.generateAuthenticationToken(user);

            return new AuthenticationResponse(authToken, "User login was successful");

        } catch (BadCredentialsException e) {
            throw new AuthenticationErrorException("Invalid email or password");
        } catch (UsernameNotFoundException e) {
            throw new AuthenticationErrorException(e.getMessage());
        } catch (Exception e) {
            throw new AuthenticationErrorException();
        }
    }

}
