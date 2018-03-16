package com.xilixir.fortniteapi.v2.Epic.Store;

public class Catalog {
    private int refreshIntervalHrs;
    private int dailyPurchaseHrs;
    private String expiration;
    private Storefront[] storefronts;

    public Catalog(int refreshIntervalHrs, int dailyPurchaseHrs, String expiration, Storefront[] storefronts) {
        this.refreshIntervalHrs = refreshIntervalHrs;
        this.dailyPurchaseHrs = dailyPurchaseHrs;
        this.expiration = expiration;
        this.storefronts = storefronts;
    }

    public int getRefreshIntervalHrs() {
        return refreshIntervalHrs;
    }

    public int getDailyPurchaseHrs() {
        return dailyPurchaseHrs;
    }

    public String getExpiration() {
        return expiration;
    }

    public Storefront[] getStorefronts() {
        return storefronts;
    }
}
