package com.xilixir.fortniteapi.Frame;

public class Permission {
    private String resource;
    private int action;

    public Permission(String resource, int action) {
        this.resource = resource;
        this.action = action;
    }

    public String getResource() {
        return resource;
    }

    public int getAction() {
        return action;
    }
}
