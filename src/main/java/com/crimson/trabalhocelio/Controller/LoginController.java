package com.crimson.trabalhocelio.Controller;

import com.crimson.trabalhocelio.Model.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    @PostMapping
    public String form(Login login){
        return "redirect:/home";
    }
    @GetMapping
    public String a(Login login){
        return "login";
    }
}
