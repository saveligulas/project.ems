package fhv.team11.project.ems.security.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import fhv.team11.project.ems.security.error.RegistrationEmailAlreadyRegisteredException;
import fhv.team11.project.ems.security.error.RegistrationInvalidEmailException;
import fhv.team11.project.ems.security.error.RegistrationWeakPasswordException;
import fhv.team11.project.ems.security.json.AuthenticationRequest;
import fhv.team11.project.ems.security.json.AuthenticationResponse;
import fhv.team11.project.ems.security.json.RegisterRequest;
import fhv.team11.project.ems.user.Authority;
import fhv.team11.project.ems.user.User;
import fhv.team11.project.ems.user.UserRepository;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final Algorithm algorithm;
    private final JwtTokenService jwtTokenService;

    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, Algorithm algorithm, JwtTokenService jwtTokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.algorithm = algorithm;
        this.jwtTokenService = jwtTokenService;

    }

    public AuthenticationResponse register(RegisterRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();

        if (isEmailInvalid(email)) {
            throw new RegistrationInvalidEmailException();
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new RegistrationEmailAlreadyRegisteredException();
        }

        checkForWeakPassword(password);

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(password));
        user.setAuthority(Authority.USER);

        userRepository.save(user);

        return new AuthenticationResponse("User registration was successful");
    }

    private boolean isEmailInvalid(String email) {
        return false; //TODO implement Logic
    }

    private void checkForWeakPassword(String password) {
        if (password.length() < 8) {
            throw new RegistrationWeakPasswordException("Password must be at least 8 characters long");
        }

        if (password.contains(" ")) {
            throw new RegistrationWeakPasswordException("Password cannot contain whitespaces");
        }

        if (!password.matches(".*[0-9].*")) {
            throw new RegistrationWeakPasswordException("Password must contain at least one digit");
        }

        if (!password.matches(".*[A-Z].*")) {
            throw new RegistrationWeakPasswordException("Password must contain at least one uppercase letter");
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        //TODO: handle exceptions
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String authToken = jwtTokenService.generateAuthenticationToken(user);

        return new AuthenticationResponse(authToken, "User login was successful");
    }

}
