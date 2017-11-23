package com.xilixir.fortniteapi.Frame;

public class LookupResult {
    private String id;
    private String displayName;

    public LookupResult(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }
}
