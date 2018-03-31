package com.xilixir.fortniteapi.v2.Test;

import com.google.gson.Gson;
import com.xilixir.fortniteapi.v2.Configuration;
import com.xilixir.fortniteapi.v2.Credentials;
import com.xilixir.fortniteapi.v2.Epic.EpicLookup;
import com.xilixir.fortniteapi.v2.Epic.Friends.Direction;
import com.xilixir.fortniteapi.v2.Epic.Friends.Friend;
import com.xilixir.fortniteapi.v2.Epic.Friends.Status;
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
            EpicLookup lookup = api.getUserInfo("bad.player");
            Friend[] friends = api.getFriendListData(lookup.getId());
            for (Friend friend : friends){
                if (friend.getStatus() == Status.PENDING && friend.getDirection() == Direction.INBOUND) {
                    System.out.println("attempting to delete friend: " + new Gson().toJson(friend));
                    api.deleteFriendRequest(lookup.getId(), friend.getAccountId());
                }
            }
            System.out.println(new Gson().toJson(friends));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
