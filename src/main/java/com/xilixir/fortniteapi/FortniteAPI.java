package com.xilixir.fortniteapi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xilixir.fortniteapi.Frame.AlltimeStats;
import com.xilixir.fortniteapi.Frame.LookupResult;
import com.xilixir.fortniteapi.OAuth.OAuthExchange;
import com.xilixir.fortniteapi.OAuth.OAuthData;
import com.xilixir.fortniteapi.Frame.Stat;
import com.xilixir.fortniteapi.OAuth.OAuthRequest;
import com.xilixir.fortniteapi.OAuth.RequestType;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class FortniteAPI {
    private Gson gson;
    private OAuthData authData;
    private Credentials credentials;
    private OAuthExchange exchange;

    public FortniteAPI(Credentials credentials) throws Exception {
        this.credentials = credentials;
        gson = new Gson();
        if (credentials.getEmail().equals("login")
                || credentials.getPassword().equals("email")
                || credentials.getBase64hashPair().equals("base64hashPair")
                || credentials.getBase64hashPairClient().equals("base64hashPairClient")) {
            throw new Exception("email|password|base64hashPair|base64hashPairClient not set");
        }
        login();
    }

    private OAuthData login() {
        OAuthRequest initialLogin =
                new OAuthRequest(RequestType.POST, Endpoint.OAUTH_TOKEN)
                        .addField("grant_type", "password")
                        .addField("username", encode(credentials.getEmail()))
                        .addField("password", encode(credentials.getPassword()))
                        .addField("includePerms", "true")
                        .build();
        initialLogin.getHeaders().setAuthorization("basic " + credentials.getBase64hashPair());
        this.authData = gson.fromJson(initialLogin.execute(), OAuthData.class);

        OAuthRequest exchange =
                new OAuthRequest(RequestType.GET, Endpoint.OAUTH_EXCHANGE)
                        .build();
        exchange.getHeaders().setAuthorization("bearer " + this.authData.getAccess_token());
        this.exchange = gson.fromJson(exchange.execute(), OAuthExchange.class);

        OAuthRequest exchangeToken =
                new OAuthRequest(RequestType.POST, Endpoint.OAUTH_TOKEN)
                        .addField("grant_type", "exchange_code")
                        .addField("exchange_code", this.exchange.getCode())
                        .addField("includePerms", "true")
                        .addField("token_type", "eg1")
                        .build();
        exchangeToken.getHeaders().setAuthorization("basic " + credentials.getBase64hashPairClient());
        this.authData = gson.fromJson(exchangeToken.execute(), OAuthData.class);
        return this.authData;
    }

    public LookupResult lookup(String displayName) {
        OAuthRequest lookup =
                new OAuthRequest(RequestType.GET, Endpoint.lookup(displayName))
                        .build();
        lookup.getHeaders().setAuthorization("bearer " + this.authData.getAccess_token());
        return gson.fromJson(lookup.execute(), LookupResult.class);
    }

    public AlltimeStats getAllTimeStats(String username) {
        LookupResult userdata = lookup(username);
        String userId = userdata.getId();
        OAuthRequest stats =
                new OAuthRequest(RequestType.GET, Endpoint.statsAllTime(userId))
                        .build();
        stats.getHeaders().setAuthorization("bearer " + this.authData.getAccess_token());
        Stat[] st = gson.fromJson(stats.execute(), new TypeToken<Stat[]>() {
        }.getType());
        return new AlltimeStats(st);
    }

//    public OAuthData refreshToken() throws IOException {
//        HttpRequestFactory factory = new NetHttpTransport().createRequestFactory();
//        String content = "grant_type=refresh_token&refresh_token=" + this.authData.getRefresh_token() + "&includePerms=true";
//        HttpRequest request = factory.buildPostRequest(new GenericUrl(Endpoint.OAUTH_TOKEN.toString()), new ByteArrayContent("application/x-www-form-urlencoded", content.getBytes()));
//        setHeaders(request, false, false, content);
//        String response = request.execute().parseAsString();
//        this.authData = gson.fromJson(response, OAuthData.class);
//        return this.authData;
//    }

    private String encode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Gson getGson() {
        return gson;
    }
}
