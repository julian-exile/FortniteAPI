package com.xilixir.fortniteapi.v2.Epic.Friends;

public class Friend {
    private String accountId;
    private Status status;
    private Direction direction;
    private boolean favorite;

    public Friend(String accountId, Status status, Direction direction, boolean favorite) {
        this.accountId = accountId;
        this.status = status;
        this.direction = direction;
        this.favorite = favorite;
    }

    public String getAccountId() {
        return accountId;
    }

    public Status getStatus() {
        return status;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isFavorite() {
        return favorite;
    }
}
