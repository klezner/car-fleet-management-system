package pl.kl.carfleetmanagementsystem.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorizationController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
}
