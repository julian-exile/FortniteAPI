package com.xilixir.fortniteapi.v2.Test;

import com.xilixir.fortniteapi.v2.Configuration;
import com.xilixir.fortniteapi.v2.Credentials;
import com.xilixir.fortniteapi.v2.FortniteAPI;

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
            api.getStats("name");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
