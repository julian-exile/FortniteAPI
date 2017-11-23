package com.xilixir.fortniteapi.Test;

import com.xilixir.fortniteapi.Credentials;
import com.xilixir.fortniteapi.FortniteAPI;
import com.xilixir.fortniteapi.Frame.AlltimeStats;

public class Example {
    public static void main(String[] args) {
        try {
            Credentials credentials = new Credentials(
                    "your email",
                    "your password",
                    "your base64 hash pair (epic games client)",
                    "your base64 hash pair (fortnite client)"
            );
            Configuration login = new Configuration("login", Credentials.class).withDefault(credentials);
            credentials = login.read();
            FortniteAPI api = new FortniteAPI(credentials);

            AlltimeStats stats = api.getAllTimeStats("me2thanks");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
