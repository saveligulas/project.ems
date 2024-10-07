package fhv.team11.project.ems.security.controller;

import fhv.team11.project.ems.security.json.AuthenticationRequest;
import fhv.team11.project.ems.security.json.AuthenticationResponse;
import fhv.team11.project.ems.security.json.RegisterRequest;
import fhv.team11.project.ems.security.jwt.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController("/")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("login-register")
    public ModelAndView loginRegisterPage() {
        ModelAndView modelAndView = new ModelAndView("login-register");
        return modelAndView;
    }

    @PostMapping("register")
    public ModelAndView register(@ModelAttribute RegisterRequest request) {
        ModelAndView modelAndView = new ModelAndView("login-register");
        try {
            AuthenticationResponse response = authenticationService.register(request);
            modelAndView.addObject("registerMessage", "Registration Successful!");
        } catch (Exception e) {
            modelAndView.addObject("registerError", "Registration failed: " + e.getMessage());
        }
        return modelAndView;
    }

    @PostMapping("authenticate")
    @CrossOrigin
    public ModelAndView authenticate(@ModelAttribute AuthenticationRequest request, HttpServletResponse servlet) {
        ModelAndView modelAndView = new ModelAndView("login-register");
        try {
            AuthenticationResponse response = authenticationService.authenticate(request);
            servlet.addCookie(new Cookie("authToken", response.getAuthToken()));
            modelAndView.addObject("loginMessage", "Login successful!");
        } catch (Exception e) {
            modelAndView.addObject("loginError", "Authentication failed: " + e.getMessage());
        }
        return modelAndView;
    }
}
