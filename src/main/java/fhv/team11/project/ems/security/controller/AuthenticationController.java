package fhv.team11.project.ems.security.controller;

import fhv.team11.project.ems.security.error.AuthenticationErrorException;
import fhv.team11.project.ems.security.error.RegistrationError;
import fhv.team11.project.ems.security.json.AuthenticationRequest;
import fhv.team11.project.ems.security.json.AuthenticationResponse;
import fhv.team11.project.ems.security.json.RegisterRequest;
import fhv.team11.project.ems.security.jwt.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
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
    public ModelAndView registerPage() {
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
            if (bindingResult.hasFieldErrors("email")) {
                errorModel.addObject("hasEmailError", true);
            }
            errorModel.addObject("registerRequest", registerRequest);
            return errorModel;
        }

        try {
            AuthenticationResponse response = authenticationService.register(registerRequest.getEmail(), registerRequest.getPassword());
            return new ModelAndView("redirect:/login");
        } catch (RegistrationError e) {
            errorModel.addObject("hasEmailError", true);
            errorModel.addObject("emailError", e.getMessage());
            return errorModel;
        }
    }
    /*
    @GetMapping("/success")
    public ResponseEntity<String> successPage() {
        return ResponseEntity.accepted().body("success");
    }
    */
    @GetMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @GetMapping("/login")
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("authenticationRequest", new AuthenticationRequest());
        return modelAndView;
    }

    @PostMapping("/authenticate")
    public ModelAndView authenticate(@Valid @ModelAttribute("authenticationRequest") AuthenticationRequest request,
                                    BindingResult bindingResult,
                                    HttpServletResponse servlet,
                                    HttpSession session,
                                    RedirectAttributes redirectAttributes) {
        ModelAndView errorModelAndView = new ModelAndView("redirect:/login");

        if (!bindingResult.hasFieldErrors("email")) {
            session.setAttribute("cachedEmail", request.getEmail());
        } else {
            session.removeAttribute("cachedEmail");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("hasError", "Please enter a valid email address and enter a password");
            return errorModelAndView;
        }

        try {
            AuthenticationResponse response = authenticationService.authenticate(request);
            servlet.addCookie(new Cookie("authToken", response.getAuthToken()));
            session.setAttribute("authenticatedEmail", request.getEmail());

        } catch (AuthenticationErrorException e) {
            redirectAttributes.addFlashAttribute("hasError", "Authentication failed! Please check your credentials");
            return errorModelAndView;
        }
        return new ModelAndView("redirect:/index");
    }
}
