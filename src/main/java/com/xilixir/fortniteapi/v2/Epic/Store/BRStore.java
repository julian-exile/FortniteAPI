package com.xilixir.fortniteapi.v2.Epic.Store;

public class BRStore {
    private Storefront weekly;
    private Storefront daily;

    public BRStore(Storefront weekly, Storefront daily) {
        this.weekly = weekly;
        this.daily = daily;
    }

    public Storefront getWeekly() {
        return weekly;
    }

    public Storefront getDaily() {
        return daily;
    }
}
