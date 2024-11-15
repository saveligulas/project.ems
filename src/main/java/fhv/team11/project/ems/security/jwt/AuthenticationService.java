package fhv.team11.project.ems.security.jwt;

import fhv.team11.project.ems.commons.validation.domain.DomainValidatorFactory;
import fhv.team11.project.ems.security.error.UserNotFoundException;
import fhv.team11.project.ems.security.transfer.domain.error.RegisterRequestValidationException;
import fhv.team11.project.ems.security.transfer.RegisterRequest;
import fhv.team11.project.ems.user.repo.Role;
import fhv.team11.project.ems.user.repo.UserJDBCRepository;
import fhv.team11.project.ems.security.error.*;
import fhv.team11.project.ems.security.transfer.AuthenticationRequest;
import fhv.team11.project.ems.security.transfer.AuthenticationResponse;
import fhv.team11.project.ems.user.repo.entity.UserJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class AuthenticationService {
    private final UserJDBCRepository userJDBCRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final DomainValidatorFactory factory;

    @Autowired
    public AuthenticationService(UserJDBCRepository userJDBCRepository, AuthenticationManager authenticationManager, JwtTokenService jwtTokenService, DomainValidatorFactory domainValidatorFactory) {
        this.userJDBCRepository = userJDBCRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.factory = domainValidatorFactory;
    }

    //!ALERT - do not encode the password here
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        BindingResult bindingResult = factory.getValidator(RegisterRequest.class).validate(registerRequest);
        if (bindingResult.hasErrors()) {
            throw new RegisterRequestValidationException(bindingResult, "register");
        }

        String email = registerRequest.getEmail();
        String password = registerRequest.getPassword();

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
