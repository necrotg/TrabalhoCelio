package com.crimson.trabalhocelio.Repositories;

import com.crimson.trabalhocelio.Model.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ClienteRepository extends CrudRepository<Cliente,Long> {
    Cliente findByNome(byte[] nome);
    ArrayList<Cliente> existsByNome(String nome);
}
