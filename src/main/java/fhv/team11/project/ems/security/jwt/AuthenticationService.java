package fhv.team11.project.ems.security.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import fhv.team11.project.ems.security.error.RegistrationEmailAlreadyRegisteredException;
import fhv.team11.project.ems.security.json.AuthenticationRequest;
import fhv.team11.project.ems.security.json.AuthenticationResponse;
import fhv.team11.project.ems.security.json.RegisterRequest;
import fhv.team11.project.ems.user.Authority;
import fhv.team11.project.ems.user.User;
import fhv.team11.project.ems.user.UserRepository;
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
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RegistrationEmailAlreadyRegisteredException();
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAuthority(Authority.USER);

        userRepository.save(user);

        return new AuthenticationResponse("User registration was successful");
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
