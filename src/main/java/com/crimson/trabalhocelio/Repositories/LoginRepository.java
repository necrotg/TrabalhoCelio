package com.crimson.trabalhocelio.Repositories;

import com.crimson.trabalhocelio.Model.Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface LoginRepository extends CrudRepository<Login,Long> {
    Login findByUsername(String username);
}
