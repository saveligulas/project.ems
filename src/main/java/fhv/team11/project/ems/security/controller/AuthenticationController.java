package fhv.team11.project.ems.security.controller;

import fhv.team11.project.ems.security.json.AuthenticationRequest;
import fhv.team11.project.ems.security.json.AuthenticationResponse;
import fhv.team11.project.ems.security.json.RegisterRequest;
import fhv.team11.project.ems.security.jwt.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/login-register")
    public ModelAndView loginRegisterPage() {
        ModelAndView modelAndView = new ModelAndView("login-register");
        return modelAndView;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequest request, RedirectAttributes redirectAttributes) {
        try {
            AuthenticationResponse response = authenticationService.register(request);
            redirectAttributes.addFlashAttribute("registerMessage", "Registration Successful!");
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
            redirectAttributes.addFlashAttribute("registerError", "Registration failed: " + e.getMessage());
        }
        return "redirect:/login-register";
    }

    @PostMapping("/authenticate")
    public String authenticate(@ModelAttribute AuthenticationRequest request, HttpServletResponse servlet, RedirectAttributes redirectAttributes) {
        try {
            AuthenticationResponse response = authenticationService.authenticate(request);
            servlet.addCookie(new Cookie("authToken", response.getAuthToken()));
            redirectAttributes.addFlashAttribute("loginMessage", "Login successful!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("loginError", "Authentication failed: " + e.getMessage());
        }
        return "redirect:/login-register";
    }
}
