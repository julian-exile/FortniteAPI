package com.xilixir.fortniteapi.v2.Epic;

public class EpicStat {
    private String name;
    private long value;
    private String window;
    private int ownerType;

    public EpicStat(String name, long value, String window, int ownerType) {
        this.name = name;
        this.value = value;
        this.window = window;
        this.ownerType = ownerType;
    }

    public String getName() {
        return name;
    }

    public long getValue() {
        return value;
    }

    public String getWindow() {
        return window;
    }

    public int getOwnerType() {
        return ownerType;
    }
}
