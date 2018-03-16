package com.xilixir.fortniteapi.v2.Epic.Store;

public class Price {
    private String currencyType;
    private String currencySubType;
    private int regularPrice;
    private int finalPrice;
    private String saleExpiration;
    private int basePrice;

    public Price(String currencyType, String currencySubType, int regularPrice, int finalPrice, String saleExpiration, int basePrice) {
        this.currencyType = currencyType;
        this.currencySubType = currencySubType;
        this.regularPrice = regularPrice;
        this.finalPrice = finalPrice;
        this.saleExpiration = saleExpiration;
        this.basePrice = basePrice;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public String getCurrencySubType() {
        return currencySubType;
    }

    public int getRegularPrice() {
        return regularPrice;
    }

    public int getFinalPrice() {
        return finalPrice;
    }

    public String getSaleExpiration() {
        return saleExpiration;
    }

    public int getBasePrice() {
        return basePrice;
    }
}
