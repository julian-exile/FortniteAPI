package com.xilixir.fortniteapi.OAuth;

import com.xilixir.fortniteapi.Frame.Permission;

public class OAuthData {
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
    private Permission[] perms;

    public OAuthData(String access_token, long expires_in, String expires_at, String token_type, String refresh_token, long refresh_expires, String refresh_expires_at, String account_id, String client_id, boolean internal_client, String client_service, Permission[] perms) {
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.expires_at = expires_at;
        this.token_type = token_type;
        this.refresh_token = refresh_token;
        this.refresh_expires = refresh_expires;
        this.refresh_expires_at = refresh_expires_at;
        this.account_id = account_id;
        this.client_id = client_id;
        this.internal_client = internal_client;
        this.client_service = client_service;
        this.perms = perms;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public long getRefresh_expires() {
        return refresh_expires;
    }

    public String getRefresh_expires_at() {
        return refresh_expires_at;
    }

    public String getAccount_id() {
        return account_id;
    }

    public Permission[] getPerms() {
        return perms;
    }

    public String getAccess_token() {
        return access_token;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public String getExpires_at() {
        return expires_at;
    }

    public String getToken_type() {
        return token_type;
    }

    public String getClient_id() {
        return client_id;
    }

    public boolean isInternal_client() {
        return internal_client;
    }

    public String getClient_service() {
        return client_service;
    }
}
