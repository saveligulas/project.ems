package fhv.team11.project.ems.security.jwt;

import fhv.team11.project.ems.user.repo.UserJDBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UserJDBCRepository userJDBCRepository;

    @Autowired
    public JwtUserDetailsService(UserJDBCRepository userJDBCRepository) {
        this.userJDBCRepository = userJDBCRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userJDBCRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
