package fhv.team11.project.ems.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test")
    @CrossOrigin
    public ResponseEntity<String> testAuthentication() {
        return ResponseEntity.ok("Authentication works!");
    }
}
