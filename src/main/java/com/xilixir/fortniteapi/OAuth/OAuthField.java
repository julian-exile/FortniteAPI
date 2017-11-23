package com.xilixir.fortniteapi.OAuth;

public class OAuthField {
    private String name;
    private String value;

    public OAuthField(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
