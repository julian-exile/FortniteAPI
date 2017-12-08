package com.xilixir.fortniteapi.v2.Epic;

public class EpicPermission {
    private String resource;
    private int action;

    public EpicPermission(String resource, int action) {
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
