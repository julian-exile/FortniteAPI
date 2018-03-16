package com.xilixir.fortniteapi.v2.Epic.Store;

public class CatalogEntry {
    private String offerId;
    private String devName;
    private String offerType;
    private Price[] prices;
//    private Object[] categories;
    private int dailyLimit;
    private int weeklyLimit;
    private int monthlyLimit;
    private String[] appStoreId;
//    private Object[] requirements;
    private CatalogEntryMetadata[] metaInfo;
    private String catalogGroup;
    private int catalogGroupPriority;
    private int sortPriority;
    private String title;
    private String shortDescription;
    private String description;
    private String displayAssetPath;
//    private Object[] itemGrants;


    public CatalogEntry(String offerId, String devName, String offerType, Price[] prices, int dailyLimit, int weeklyLimit, int monthlyLimit, String[] appStoreId, CatalogEntryMetadata[] metaInfo, String catalogGroup, int catalogGroupPriority, int sortPriority, String title, String shortDescription, String description, String displayAssetPath) {
        this.offerId = offerId;
        this.devName = devName;
        this.offerType = offerType;
        this.prices = prices;
        this.dailyLimit = dailyLimit;
        this.weeklyLimit = weeklyLimit;
        this.monthlyLimit = monthlyLimit;
        this.appStoreId = appStoreId;
        this.metaInfo = metaInfo;
        this.catalogGroup = catalogGroup;
        this.catalogGroupPriority = catalogGroupPriority;
        this.sortPriority = sortPriority;
        this.title = title;
        this.shortDescription = shortDescription;
        this.description = description;
        this.displayAssetPath = displayAssetPath;
    }

    public String getOfferId() {
        return offerId;
    }

    public String getDevName() {
        return devName;
    }

    public String getOfferType() {
        return offerType;
    }

    public Price[] getPrices() {
        return prices;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }

    public int getWeeklyLimit() {
        return weeklyLimit;
    }

    public int getMonthlyLimit() {
        return monthlyLimit;
    }

    public String[] getAppStoreId() {
        return appStoreId;
    }

    public CatalogEntryMetadata[] getMetaInfo() {
        return metaInfo;
    }

    public String getCatalogGroup() {
        return catalogGroup;
    }

    public int getCatalogGroupPriority() {
        return catalogGroupPriority;
    }

    public int getSortPriority() {
        return sortPriority;
    }

    public String getTitle() {
        return title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getDisplayAssetPath() {
        return displayAssetPath;
    }
}
