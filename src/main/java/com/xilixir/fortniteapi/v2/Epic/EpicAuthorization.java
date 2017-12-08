package com.xilixir.fortniteapi.v2.Epic;

public class EpicAuthorization {
    private String access_token;
    private long expires_in;
    private String expires_at;
    private String token_type;
    private String refresh_token;
    private long refresh_expires;
    private String refresh_expires_at;
    private String account_id;
    private String client_id;
    private boolean internal_client;
    private String client_service;
    private EpicPermission[] perms;

    public EpicAuthorization() {
        //
    }

    public void setExpiresIn(long expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefreshToken() {
        return refresh_token;
    }

    public long getRefreshExpires() {
        return refresh_expires;
    }

    public String getRefreshExpiresAt() {
        return refresh_expires_at;
    }

    public String getAccountId() {
        return account_id;
    }

    public EpicPermission[] getPermissions() {
        return perms;
    }

    public String getAccessToken() {
        return access_token;
    }

    public long getExpiresIn() {
        return expires_in;
    }

    public String getExpiresAt() {
        return expires_at;
    }

    public String getTokenType() {
        return token_type;
    }

    public String getClientId() {
        return client_id;
    }

    public boolean isInternalClient() {
        return internal_client;
    }

    public String getClientService() {
        return client_service;
    }
}
