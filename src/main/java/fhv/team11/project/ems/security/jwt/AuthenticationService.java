package fhv.team11.project.ems.security.jwt;

import fhv.team11.project.ems.commons.validation.domain.BeanPropertyBindingResultBuilder;
import fhv.team11.project.ems.commons.validation.domain.DomainValidatorFactory;
import fhv.team11.project.ems.domain.commons.exception.DomainValidationException;
import fhv.team11.project.ems.domain.user.User;
import fhv.team11.project.ems.security.error.UserNotFoundException;
import fhv.team11.project.ems.security.transfer.domain.error.AuthenticationRequestValidationException;
import fhv.team11.project.ems.security.permission.role.Role;
import fhv.team11.project.ems.user.domain.UserDomainDatabaseFactory;
import fhv.team11.project.ems.user.entity.UserEntityRepository;
import fhv.team11.project.ems.user.repo.UserJDBCRepository;
import fhv.team11.project.ems.security.error.*;
import fhv.team11.project.ems.security.transfer.AuthenticationRequest;
import fhv.team11.project.ems.security.transfer.AuthenticationResponse;
import fhv.team11.project.ems.user.entity.UserJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;

import java.util.List;

@Service
public class AuthenticationService {
    private final UserJDBCRepository userJDBCRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final DomainValidatorFactory factory;
    private final UserEntityRepository userEntityRepository;
    private final UserDomainDatabaseFactory userDomainDatabaseFactory;

    @Autowired
    public AuthenticationService(UserJDBCRepository userJDBCRepository, AuthenticationManager authenticationManager, JwtTokenService jwtTokenService, DomainValidatorFactory domainValidatorFactory, UserEntityRepository userEntityRepository, UserDomainDatabaseFactory userDomainDatabaseFactory) {
        this.userJDBCRepository = userJDBCRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.factory = domainValidatorFactory;
        this.userEntityRepository = userEntityRepository;
        this.userDomainDatabaseFactory = userDomainDatabaseFactory;
    }

    //!ALERT - do not encode the password here
    public AuthenticationResponse register(String email, String password) throws DomainValidationException, RegistrationException {
        if (userJDBCRepository.findByEmail(email).isPresent()) {
            throw new RegistrationException("email", "Email is already taken");
        }

        User user = new User(
                null,
                email,
                password,
                email,
                List.of(Role.CUSTOMER, Role.ADMIN, Role.EMPLOYEE),
                List.of(),
                null,
                null,
                null,
                null
        );

        userDomainDatabaseFactory.persist(user);

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
            throw new AuthenticationRequestValidationException("Invalid email or password");
        } catch (UsernameNotFoundException e) {
            throw new AuthenticationRequestValidationException(e.getMessage());
        } catch (Exception e) {
            throw new AuthenticationRequestValidationException();
        }
    }

}
