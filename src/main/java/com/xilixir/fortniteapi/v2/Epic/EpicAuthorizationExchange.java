package com.xilixir.fortniteapi.v2.Epic;

public class EpicAuthorizationExchange {
    private long expiresInSeconds;
    private String code;
    private String creatingClientId;

    public EpicAuthorizationExchange() {
        //
    }

    public long getExpiresInSeconds() {
        return expiresInSeconds;
    }

    public String getCode() {
        return code;
    }

    public String getCreatingClientId() {
        return creatingClientId;
    }
}
