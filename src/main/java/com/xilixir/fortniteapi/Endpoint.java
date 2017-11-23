package com.xilixir.fortniteapi;

public enum Endpoint {
    OAUTH_TOKEN ("https://account-public-service-prod03.ol.epicgames.com/account/api/oauth/token"),
    OAUTH_EXCHANGE ("https://account-public-service-prod03.ol.epicgames.com/account/api/oauth/exchange"),
    OAUTH_VERIFY ("https://account-public-service-prod03.ol.epicgames.com/account/api/oauth/verify?includePerms=true");
    private String url;

    Endpoint(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return this.url;
    }

    public static String lookup(String username){
        return "https://persona-public-service-prod06.ol.epicgames.com/persona/api/public/account/lookup?q=" + username;
    }

    public static String statsAllTime(String accountId){
        return "https://fortnite-public-service-prod11.ol.epicgames.com/fortnite/api/stats/accountId/" + accountId + "/bulk/window/alltime";
    }
}
