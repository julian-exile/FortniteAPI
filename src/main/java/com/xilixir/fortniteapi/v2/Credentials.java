package com.xilixir.fortniteapi.v2;

public class Credentials {
    private String email;
    private String password;
    private String base64hashPair;
    private String base64hashPairClient;

    public Credentials(String email, String password, String base64hashPair, String base64hashPairClient) {
        this.email = email;
        this.password = password;
        this.base64hashPair = base64hashPair;
        this.base64hashPairClient = base64hashPairClient;
    }

    public String getBase64hashPairClient() {
        return base64hashPairClient;
    }

    public String getBase64hashPair() {
        return base64hashPair;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
