package cgg.spring_security.spring_security.config.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableMethodSecurity
@RequestMapping("/public")
public class HomeController {
    @GetMapping("/home")
    @PreAuthorize("hasRole('NORMAL')")
    public String home() {

        return "this is home page";
    }

    @GetMapping("/login")
    @PreAuthorize("hasRole('NORMAL')")
    public String login() {

        return "this is login page";
    }

    @GetMapping("/register")
    public String register() {

        return "this is register page";
    }
}
