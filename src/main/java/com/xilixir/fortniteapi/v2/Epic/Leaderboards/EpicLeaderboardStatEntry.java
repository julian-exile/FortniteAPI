package com.xilixir.fortniteapi.v2.Epic.Leaderboards;

public class EpicLeaderboardStatEntry {
    private String accountId;
    private long value;
    private long rank;

    public EpicLeaderboardStatEntry(String accountId, long value, long rank) {
        this.accountId = accountId;
        this.value = value;
        this.rank = rank;
    }

    public String getAccountId() {
        return accountId;
    }

    public long getValue() {
        return value;
    }

    public long getRank() {
        return rank;
    }
}
