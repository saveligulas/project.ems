package fhv.team11.project.ems.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JWTVerifier verifier;
    private final JwtUserDetailsService userDetailsService;
    private final Algorithm algorithm;

    @Autowired
    public JwtAuthenticationFilter(JWTVerifier verifier, JwtUserDetailsService userDetailsService, Algorithm algorithm) {
        this.verifier = verifier;
        this.userDetailsService = userDetailsService;
        this.algorithm = algorithm;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("In the Jwt Authentication Filter");
        Cookie[] cookies = request.getCookies();
        String authHeader = request.getHeader("Authorization");

        if (cookies == null && authHeader == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = null;

        if (authHeader != null) {
            token = authHeader;
        } else {
            for (Cookie cookie : cookies) {
                if ("authToken".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
            if(token == null) {
                filterChain.doFilter(request,response);
                return;
            }
        }

        DecodedJWT jwt = null;

        try {
            jwt = JWT.require(algorithm)
                    .build()
                    .verify(token);
        } catch (JWTVerificationException e) {
            filterChain.doFilter(request, response);
            return;
        }

        String username = jwt.getSubject();

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (userDetails.getUsername().equals(username)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("Token:" + token + " was valid \n" + authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
