package com.softserve.mosquito.dtos;

import com.softserve.mosquito.entities.User;

public class TrelloInfoDto {

    private Long id;
    private User user;
    private String userTrelloName;
    private String userTrelloKey;
    private String userTrelloToken;

    public TrelloInfoDto() {
    }

    public TrelloInfoDto(Long id, User user, String userTrelloName, String userTrelloKey, String userTrelloToken) {
        this.id = id;
        this.user = user;
        this.userTrelloName = userTrelloName;
        this.userTrelloKey = userTrelloKey;
        this.userTrelloToken = userTrelloToken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return "TrelloInfoDto{" +
                "id=" + id +
                ", userId=" + user +
                ", userTrelloName='" + userTrelloName + '\'' +
                ", userTrelloKey='" + userTrelloKey + '\'' +
                ", userTrelloToken='" + userTrelloToken + '\'' +
                '}';
    }
}
