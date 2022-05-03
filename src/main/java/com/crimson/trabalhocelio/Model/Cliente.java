package com.crimson.trabalhocelio.Model;

import com.crimson.trabalhocelio.utils.Chave;
import com.crimson.trabalhocelio.utils.Encryption;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class Cliente implements Serializable {
    private static final int serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_cliente;

    private byte[] nome;
    private byte[] cpf;
    private byte[] rg;
    private Date created;
    private Date updated;
    private Date deleted;

    public static int getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public byte[] getNome() {
        return nome;
    }

    public void setNome(byte[] nome) {
        Encryption encryption = new Encryption();
        this.nome = encryption.encrypt(nome);
    }

    public byte[] getCpf() {
        return cpf;
    }

    public void setCpf(byte[] cpf) {
        Encryption encryption = new Encryption();
        this.cpf = encryption.encrypt(cpf);
    }

    public byte[] getRg() {
        return rg;
    }

    public void setRg(byte[] rg) {
        Encryption encryption = new Encryption();
        this.rg = encryption.encrypt(rg);
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
}
