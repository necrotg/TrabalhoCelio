package com.crimson.trabalhocelio.Controller;

import com.crimson.trabalhocelio.Model.Login;
import com.crimson.trabalhocelio.Repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/criarConta")
public class CriarContaController {

    @Autowired
    private LoginRepository log;

    @PostMapping
    public String form(Login login){
        log.save(login);
        return "redirect:/login";
    }
    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String criarConta(){
        return "/criarConta";
    }
}
