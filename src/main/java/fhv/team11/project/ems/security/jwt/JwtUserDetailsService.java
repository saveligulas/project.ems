package fhv.team11.project.ems.security.jwt;

import fhv.team11.project.ems.commons.user.UserDatabaseService;
import fhv.team11.project.ems.commons.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UserDatabaseService userDatabaseService;

    @Autowired
    public JwtUserDetailsService(UserDatabaseService userDatabaseService) {
        this.userDatabaseService = userDatabaseService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDatabaseService.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
