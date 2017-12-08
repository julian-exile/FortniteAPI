package com.xilixir.fortniteapi.v2.Epic.Leaderboards;

public class EpicLeaderboardStats {
    private String statWindow;
    private String statName;
    private EpicLeaderboardStatEntry[] entries;

    public EpicLeaderboardStats(String statWindow, String statName, EpicLeaderboardStatEntry[] entries) {
        this.statWindow = statWindow;
        this.statName = statName;
        this.entries = entries;
    }

    public String getStatWindow() {
        return statWindow;
    }

    public String getStatName() {
        return statName;
    }

    public EpicLeaderboardStatEntry[] getEntries() {
        return entries;
    }
}
