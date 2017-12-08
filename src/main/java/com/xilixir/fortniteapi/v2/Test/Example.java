package com.xilixir.fortniteapi.v2.Test;

import com.xilixir.fortniteapi.v2.Configuration;
import com.xilixir.fortniteapi.v2.Credentials;
import com.xilixir.fortniteapi.v2.Epic.EpicLookup;
import com.xilixir.fortniteapi.v2.FortniteAPI;
import com.xilixir.fortniteapi.v2.Stats;

import java.io.IOException;

public class Example {
    public static void main(String[] args){
        Configuration login = new Configuration("login", Credentials.class);
        Credentials credentials = login.read();
        FortniteAPI api = new FortniteAPI(credentials);
        try {
            api.authenticate();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            EpicLookup lookup = api.getUserInfo("xilixir");
            Stats stats = api.getStats(lookup.getId());
//            Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
//            System.out.println(gson.toJson(lookup));
//            System.out.println(gson.toJson(stats));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
