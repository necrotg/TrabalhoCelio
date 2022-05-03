package com.crimson.trabalhocelio.Controller;

import com.crimson.trabalhocelio.Model.Cliente;
import com.crimson.trabalhocelio.Model.ClienteString;
import com.crimson.trabalhocelio.Repositories.ClienteRepository;
import com.crimson.trabalhocelio.utils.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ClienteRepository cr;
    Encryption encryption = new Encryption();

    @GetMapping
    public ModelAndView listaClientes(){
        ModelAndView mv = new ModelAndView("Home");
        Iterable<Cliente> clientes = cr.findAll();
        ArrayList<ClienteString> clientesString = new ArrayList<>();
        clientes.forEach(cliente -> {
            ClienteString clienteString = new ClienteString();
            clienteString.setId_cliente(cliente.getId_cliente());
            clienteString.setNome(new String(encryption.decrypt(cliente.getNome())));
            clienteString.setCpf(new String(encryption.decrypt(cliente.getCpf())));
            clienteString.setRg(new String(encryption.decrypt(cliente.getRg())));
            clienteString.setCreated(cliente.getCreated());
            clienteString.setUpdated(cliente.getUpdated());
            clienteString.setDeleted(cliente.getDeleted());
            clientesString.add(clienteString);
        });
        mv.addObject("clientes",clientesString);
        return mv;
    }

    @GetMapping("/encrypt")
    public ModelAndView listaClientesEncrypt(){
        ModelAndView mv = new ModelAndView("HomeEncrypted");
        Iterable<Cliente> clientes = cr.findAll();
        mv.addObject("clientes",clientes);
        return mv;
    }
    @GetMapping(value="/home/{nome}")
    public ModelAndView listaClientes(@PathVariable("nome") String nome){
        ModelAndView mv = new ModelAndView("home");
        Iterable<Cliente> clientes = cr.findAll();
        AtomicReference<Cliente> cliente1 = new AtomicReference<>(new Cliente());
        clientes.forEach(cliente -> {
            if(new String(encryption.decrypt(cliente.getNome())).contains(nome.replace("_"," "))){
                cliente1.set(cr.findByNome(cliente.getNome()));
                mv.addObject("cliente",cliente1);
            }
        });
        mv.addObject("cliente",cliente1);
        return mv;
    }

    @PostMapping
    public String form(ClienteString cliente2){
        return "redirect:/home/{"+cliente2.getNome().replace(" ","_")+"}";
    }
}
