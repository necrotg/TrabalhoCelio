package com.crimson.trabalhocelio.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Encryption {
    Chave chave = new Chave();
    public byte[] encrypt(byte[] bytes) {
        try{
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(chave.getChave().getBytes(), "AES"));
            return cipher.doFinal(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public byte[] decrypt(byte[] bytes)  {
        try{
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(chave.getChave().getBytes(), "AES"));
            return cipher.doFinal(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
