package com.xilixir.fortniteapi.v2;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xilixir.fortniteapi.v2.Epic.EpicAuthorization;
import com.xilixir.fortniteapi.v2.Epic.EpicAuthorizationExchange;
import com.xilixir.fortniteapi.v2.Epic.EpicLookup;
import com.xilixir.fortniteapi.v2.Epic.EpicStat;
import com.xilixir.fortniteapi.v2.Epic.Store.BRStore;
import com.xilixir.fortniteapi.v2.Epic.Store.Catalog;
import com.xilixir.fortniteapi.v2.Epic.Store.Storefront;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FortniteAPI {
    private HttpRequestFactory factory;
    private ScheduledExecutorService scheduler;
    private EpicAuthorization auth;
    private Credentials credentials;

    private static String authTokenEndpoint = "https://account-public-service-prod03.ol.epicgames.com/account/api/oauth/token";

    public FortniteAPI(Credentials credentials) {
        this.credentials = credentials;
        this.factory = new NetHttpTransport().createRequestFactory();
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    public void authenticate() throws IOException {
        log("Starting Authentication...");
        Gson gson = new Gson();

        // initial login
        GenericUrl tokenUrl = new GenericUrl(authTokenEndpoint);
        String initialLoginPayload = payloadBuilder(
                new String[]{
                        "grant_type",
                        "username",
                        "password",
                        "includePerms"
                },
                new String[]{
                        "password",
                        encode(credentials.getEmail()),
                        encode(credentials.getPassword()),
                        "true"
                }
        );
        ByteArrayContent initialLoginContent = new ByteArrayContent("application/x-www-form-urlencoded", initialLoginPayload.getBytes());
        HttpRequest initialLoginRequest = factory.buildPostRequest(tokenUrl, initialLoginContent);
        initialLoginRequest.getHeaders().setAuthorization("basic " + credentials.getBase64hashPair());

        // initial login response
        String initialLoginResponse = initialLoginRequest.execute().parseAsString();
        this.auth = gson.fromJson(initialLoginResponse, EpicAuthorization.class);

        // exchange-1
        String authExchangeEndpoint = "https://account-public-service-prod03.ol.epicgames.com/account/api/oauth/exchange";
        GenericUrl exchangeUrl = new GenericUrl(authExchangeEndpoint);
        HttpRequest exchangeRequest = factory.buildGetRequest(exchangeUrl);
        exchangeRequest.getHeaders().setAuthorization("bearer " + this.auth.getAccessToken());

        // exchange-1 response
        String exchangeResponse = exchangeRequest.execute().parseAsString();
        EpicAuthorizationExchange exchange = gson.fromJson(exchangeResponse, EpicAuthorizationExchange.class);

        // exchange-2
        String exchangeTokenPayload = payloadBuilder(
                new String[]{
                        "grant_type",
                        "exchange_code",
                        "includePerms",
                        "token_type"
                },
                new String[]{
                        "exchange_code",
                        exchange.getCode(),
                        "true",
                        "eg1"
                }
        );

        ByteArrayContent exchangeTokenContent = new ByteArrayContent("application/x-www-form-urlencoded", exchangeTokenPayload.getBytes());
        HttpRequest exchangeTokenRequest = factory.buildPostRequest(tokenUrl, exchangeTokenContent);
        exchangeTokenRequest.getHeaders().setAuthorization("basic " + credentials.getBase64hashPairClient());

        // exchange-2 response
        String exchangeTokenResponse = exchangeTokenRequest.execute().parseAsString();
        this.auth = gson.fromJson(exchangeTokenResponse, EpicAuthorization.class);
        log("Authenticated successfully!");
        this.scheduleTokenRefresh();
    }

    public Stats getStats(String userId) throws IOException {
        // request
        GenericUrl url = new GenericUrl("https://fortnite-public-service-prod11.ol.epicgames.com/fortnite/api/stats/accountId/" + userId + "/bulk/window/alltime");
        HttpRequest request = factory.buildGetRequest(url);

        // headers
        request.getHeaders().setAuthorization("bearer " + this.auth.getAccessToken());

        // convert multiple EpicStat objects into one Stats object
        String json = request.execute().parseAsString();
        EpicStat[] rawStats = new Gson().fromJson(json, new TypeToken<EpicStat[]>() {
        }.getType());
        StringBuilder str = new StringBuilder();
        for (EpicStat s : rawStats) {
            str.append(",\"").append(s.getName()).append("\":").append(s.getValue());
        }
        String jsonStats = "{" + str.toString().substring(1) + "}";
        Stats stats = new Gson().fromJson(jsonStats, Stats.class);
        stats.calculate();
        log("Got stats for id '" + userId + "'");
        return stats;
    }

    public Catalog getAllStoreItems() throws IOException {
        // request
        GenericUrl url = new GenericUrl("https://fortnite-public-service-prod11.ol.epicgames.com/fortnite/api/storefront/v2/catalog?rvn=5479");
        HttpRequest request = factory.buildGetRequest(url);

        // headers
        request.getHeaders().setAuthorization("bearer " + this.auth.getAccessToken());

        String json = request.execute().parseAsString();
        Catalog catalog = new Gson().fromJson(json, Catalog.class);
        log("Retrieved catalog info");
        return catalog;
    }

    public BRStore getBRStoreItems() throws IOException {
        Catalog catalog = getAllStoreItems();
        Storefront brDaily = null, brWeekly = null;
        for (Storefront sf : catalog.getStorefronts()){
            String sfName = sf.getName();
            if (sfName.equals("BRWeeklyStorefront")){
                brWeekly = sf;
            } else if (sfName.equals("BRDailyStorefront")){
                brDaily = sf;
            }
        }
        return new BRStore(brWeekly, brDaily);
    }

    public EpicLookup getUserInfo(String username) throws IOException {
        // request
        GenericUrl url = new GenericUrl("https://persona-public-service-prod06.ol.epicgames.com/persona/api/public/account/lookup?q=" + username);
        HttpRequest request = factory.buildGetRequest(url);

        // headers
        request.getHeaders().setAuthorization("bearer " + this.auth.getAccessToken());

        String json = request.execute().parseAsString();
        EpicLookup lookup = new Gson().fromJson(json, EpicLookup.class);
        log("Looked up user '" + lookup.getDisplayName() + "{" + lookup.getId() + "}'");
        return lookup;
    }

    private void refreshToken() {
        try {
            log("Refreshing token...");
            Gson gson = new Gson();

            // refresh request
            GenericUrl url = new GenericUrl(authTokenEndpoint);
            String refreshTokenPayload = payloadBuilder(
                    new String[]{
                            "grant_type",
                            "refresh_token",
                            "includePerms"
                    },
                    new String[]{
                            "refresh_token",
                            this.auth.getRefreshToken(),
                            "true"
                    }
            );

            ByteArrayContent refreshTokenContent = new ByteArrayContent("application/x-www-form-urlencoded", refreshTokenPayload.getBytes());
            HttpRequest refreshTokenRequest = factory.buildPostRequest(url, refreshTokenContent);
            refreshTokenRequest.getHeaders().setAuthorization("basic " + this.credentials.getBase64hashPairClient());

            // refresh response
            String refreshTokenResponse = refreshTokenRequest.execute().parseAsString();
            this.auth = gson.fromJson(refreshTokenResponse, EpicAuthorization.class);
            this.scheduleTokenRefresh();
            log("Token refresh complete");
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void scheduleTokenRefresh() {
        long t = this.auth.getExpiresIn();
        if (t > 0){
            t--;
        }
        log("Token refresh scheduled in " + t + " seconds");
        scheduler.schedule(this::refreshToken, t, TimeUnit.SECONDS);
    }

    private void log(String s) {
        System.out.println("[" + new Date().toString() + "][FortniteAPI] " + s);
    }

    private String payloadBuilder(String[] keys, String[] values) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.length; i++) {
            sb.append("&");
            sb.append(keys[i]);
            sb.append("=");
            sb.append(values[i]);
        }
        return sb.toString().substring(1);
    }

    private String encode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
