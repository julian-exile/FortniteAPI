package com.xilixir.fortniteapi.v2.Epic.Store;

public class Storefront {
    private String name;
    private CatalogEntry[] catalogEntries;

    public Storefront(String name, CatalogEntry[] catalogEntries) {
        this.name = name;
        this.catalogEntries = catalogEntries;
    }

    public String getName() {
        return name;
    }

    public CatalogEntry[] getCatalogEntries() {
        return catalogEntries;
    }
}
