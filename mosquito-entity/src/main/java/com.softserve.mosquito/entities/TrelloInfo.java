package com.softserve.mosquito.entities;

import java.io.Serializable;

public class TrelloInfo implements Serializable {
    private Long userId;
    private String userTrelloName;
    private String userTrelloKey;
    private String userTrelloToken;


    public TrelloInfo() {
    }

    public TrelloInfo(Long userId, String userTrelloName, String userTrelloKey, String userTrelloToken) {
        this.userId = userId;
        this.userTrelloName = userTrelloName;
        this.userTrelloKey = userTrelloKey;
        this.userTrelloToken = userTrelloToken;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserTrelloName() {
        return userTrelloName;
    }

    public void setUserTrelloName(String userTrelloName) {
        this.userTrelloName = userTrelloName;
    }

    public String getUserTrelloKey() {
        return userTrelloKey;
    }

    public void setUserTrelloKey(String userTrelloKey) {
        this.userTrelloKey = userTrelloKey;
    }

    public String getUserTrelloToken() {
        return userTrelloToken;
    }

    public void setUserTrelloToken(String userTrelloToken) {
        this.userTrelloToken = userTrelloToken;
    }

    @Override
    public String toString() {
        return "TrelloInfo{" +
                "userId=" + userId +
                ", userTrelloName='" + userTrelloName + '\'' +
                ", userTrelloKey='" + userTrelloKey + '\'' +
                ", userTrelloToken='" + userTrelloToken + '\'' +
                '}';
    }
}
