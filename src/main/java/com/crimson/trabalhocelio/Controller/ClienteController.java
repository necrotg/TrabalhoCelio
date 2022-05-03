package com.crimson.trabalhocelio.Controller;

import com.crimson.trabalhocelio.Model.Cliente;
import com.crimson.trabalhocelio.Model.ClienteString;
import com.crimson.trabalhocelio.Model.Login;
import com.crimson.trabalhocelio.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteRepository cr;

    @PostMapping
    public ResponseEntity<Object> saveCliente(@RequestBody ArrayList<ClienteString> clientes){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        ArrayList<Cliente> clientes1 = new ArrayList<>();
        clientes.forEach(cliente ->{
            Cliente cliente1 = new Cliente();
            cliente1.setCreated(date);
            cliente1.setUpdated(date);
            cliente1.setDeleted(date);
            cliente1.setNome(cliente.getNome().getBytes());
            cliente1.setCpf(cliente.getCpf().getBytes());
            cliente1.setRg(cliente.getRg().getBytes());
            clientes1.add(cliente1);
        });

        return ResponseEntity.status(HttpStatus.CREATED).body(cr.saveAll(clientes1));
    }
}
