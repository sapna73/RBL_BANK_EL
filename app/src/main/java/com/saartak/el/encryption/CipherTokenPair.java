package com.saartak.el.encryption;

import javax.crypto.Cipher;

public class CipherTokenPair {
    Cipher cipher;
    String token;

    public CipherTokenPair(Cipher cipher, String token) {
        this.cipher = cipher;
        this.token = token;
    }

    public Cipher getCipher() {
        return cipher;
    }

    public void setCipher(Cipher cipher) {
        this.cipher = cipher;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

