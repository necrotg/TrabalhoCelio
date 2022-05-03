package com.crimson.trabalhocelio.Model;

import com.crimson.trabalhocelio.utils.Chave;
import com.crimson.trabalhocelio.utils.Encryption;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.*;
import java.io.Serializable;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

@Entity
public class Login implements Serializable, UserDetails {

    private static final int serialVersionUID = 1;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_login;

    private String username;
    private String nome;
    private String password;
    private Date created;
    private Date updated;
    private Date deleted;

    public void setId(Long id_login) {
        this.id_login = id_login;
    }
    public Long getId() {
        return id_login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        Chave chave = new Chave();
        Encryption encryption = new Encryption();
        try{
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(chave.getChave().getBytes(), "AES"));
            this.nome = new String(encryption.encrypt(nome.getBytes()));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password =  new BCryptPasswordEncoder().encode(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        Chave chave = new Chave();
        Encryption encryption = new Encryption();
        try{
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(chave.getChave().getBytes(), "AES"));
            this.username = new String(encryption.encrypt(username.getBytes()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<GrantedAuthority>();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
