package com.crimson.trabalhocelio.Security;

import com.crimson.trabalhocelio.Model.Login;
import com.crimson.trabalhocelio.Repositories.LoginRepository;
import com.crimson.trabalhocelio.utils.Chave;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.transaction.Transactional;

@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Chave chave = new Chave();
        try{
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(chave.getChave().getBytes(), "AES"));
            username =  new String(cipher.doFinal(username.getBytes()));
        }catch (Exception e){
            e.printStackTrace();
        }
        Login login = loginRepository.findByUsername(username);
        if(login == null){
            throw new UsernameNotFoundException("Usuario não encontrado!");
        }
        return new User(login.getUsername(), login.getPassword(), true, true, true, true, login.getAuthorities());
    }
}
