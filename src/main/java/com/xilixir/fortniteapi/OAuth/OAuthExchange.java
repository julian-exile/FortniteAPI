package com.xilixir.fortniteapi.OAuth;

public class OAuthExchange {
    private long expiresInSeconds;
    private String code;
    private String creatingClientId;

    public OAuthExchange(long expiresInSeconds, String code, String creatingClientId) {
        this.expiresInSeconds = expiresInSeconds;
        this.code = code;
        this.creatingClientId = creatingClientId;
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
