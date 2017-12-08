package com.xilixir.fortniteapi.v2.Epic;

public class EpicLookup {
    private String id;
    private String displayName;

    public EpicLookup(String id, String displayName) {
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
