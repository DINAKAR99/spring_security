package cgg.spring_security.spring_security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/signin")
    public String getLoginPage() {

        return "signup";

    }

    @GetMapping("/logoutt")
    public String getlog() {

        return "logout_page";

    }
}
