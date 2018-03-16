package com.xilixir.fortniteapi.v2.Epic.Store;

public class CatalogEntryMetadata {
    private String key;
    private String value;

    public CatalogEntryMetadata(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
