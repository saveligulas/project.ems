package fhv.team11.project.ems.security.config;

import fhv.team11.project.ems.security.jwt.JwtAuthenticationFilter;
import fhv.team11.project.ems.security.jwt.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtUserDetailsService userDetailsService;
    private final JwtAuthenticationFilter authenticationFilter;

    @Autowired
    public SecurityConfig(JwtUserDetailsService userDetailsService, JwtAuthenticationFilter authenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(
                req -> req
                        .requestMatchers(HttpMethod.GET, "/verify")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST,"/register")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST,"/authenticate")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/login-register")
                        .permitAll()
                        .requestMatchers("refresh")
                        .permitAll()
                        .requestMatchers("login")
                        .permitAll()
                        .requestMatchers("error")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
        );
        http.sessionManagement(
                session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        http.userDetailsService(userDetailsService);
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
