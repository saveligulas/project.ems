package fhv.team11.project.ems.security.controller;

import fhv.team11.project.ems.security.error.RegistrationError;
import fhv.team11.project.ems.security.json.AuthenticationRequest;
import fhv.team11.project.ems.security.json.AuthenticationResponse;
import fhv.team11.project.ems.security.json.RegisterRequest;
import fhv.team11.project.ems.security.jwt.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
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

    @GetMapping("/register")
    public ModelAndView loginRegisterPage() {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("registerRequest", new RegisterRequest());
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute("registerRequest") RegisterRequest registerRequest,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        ModelAndView errorModel = new ModelAndView("register");

        if (bindingResult.hasErrors()) {
            errorModel.addObject("registerRequest", registerRequest);
            return errorModel;
        }

        try {
            AuthenticationResponse response = authenticationService.register(registerRequest.getEmail(), registerRequest.getPassword());
            return new ModelAndView("redirect:/success");
        } catch (RegistrationError e) {
            errorModel.addObject("emailError", e.getMessage());
            return errorModel;
        }
    }

    @GetMapping("/success")
    public ResponseEntity<String> successPage() {
        return ResponseEntity.accepted().body("success");
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
