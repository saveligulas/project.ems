package fhv.team11.project.ems.security.controller;

import fhv.team11.project.ems.security.error.AuthenticationErrorException;
import fhv.team11.project.ems.security.error.RegistrationError;
import fhv.team11.project.ems.security.transfer.AuthenticationRequest;
import fhv.team11.project.ems.security.transfer.AuthenticationResponse;
import fhv.team11.project.ems.security.transfer.RegisterRequest;
import fhv.team11.project.ems.security.jwt.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

    @ModelAttribute("registerRequest")
    public RegisterRequest getRegisterRequest() {
        return new RegisterRequest();
    }

    @ModelAttribute
    public AuthenticationRequest getAuthenticationRequest() {
        return new AuthenticationRequest();
    }

    @GetMapping("/register")
    public ModelAndView registerPage() {
        return new ModelAndView("register");
    }

    @PostMapping("/register/user")
    public String register(
            @Valid @ModelAttribute("registerRequest") RegisterRequest registerRequest,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        ModelAndView errorModel = new ModelAndView("register");

        if (bindingResult.hasErrors()) {
            if (bindingResult.hasFieldErrors("email")) {
                redirectAttributes.addFlashAttribute("hasEmailError", true);
            }
            redirectAttributes.addFlashAttribute("registerRequest", registerRequest);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerRequest", bindingResult);
            return "redirect:/register";
        }

        try {
            AuthenticationResponse response = authenticationService.register(registerRequest);
            return "redirect:/login";
        } catch (RegistrationError e) {
            redirectAttributes.addFlashAttribute("hasEmailError", true);
            redirectAttributes.addFlashAttribute("emailError", e.getMessage());
            return "redirect:/register";
        }
    }

    @GetMapping("/login")
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("hideHeader", true);
        return modelAndView;
    }

    @PostMapping("/authenticate")
    public String authenticate(@Valid @ModelAttribute("authenticationRequest") AuthenticationRequest request,
                                    BindingResult bindingResult,
                                    HttpServletResponse servlet,
                                    HttpSession session,
                                    RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("email")) {
            session.setAttribute("cachedEmail", request.getEmail());
        } else {
            session.removeAttribute("cachedEmail");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("hasError", "Please enter a valid email address and enter a password");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.authenticationRequest", bindingResult);
            return "redirect:/login";
        }

        try {
            AuthenticationResponse response = authenticationService.authenticate(request);
            servlet.addCookie(new Cookie("authToken", response.getAuthToken()));
            session.setAttribute("authenticatedEmail", request.getEmail());

        } catch (AuthenticationErrorException e) {
            redirectAttributes.addFlashAttribute("hasError", "Authentication failed! Please check your credentials");
            return "redirect:/login";
        }
        return "redirect:/index";
    }
}
