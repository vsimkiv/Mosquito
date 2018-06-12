package com.softserve.mosquito.dtos;

public class TrelloInfoDto {

    private Long id;
    private Long userId;
    private String userTrelloName;
    private String userTrelloKey;
    private String userTrelloToken;

    public TrelloInfoDto() {
    }

    public TrelloInfoDto(Long id, Long userId, String userTrelloName, String userTrelloKey, String userTrelloToken) {
        this.id = id;
        this.userId = userId;
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
        return "TrelloInfoDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", userTrelloName='" + userTrelloName + '\'' +
                ", userTrelloKey='" + userTrelloKey + '\'' +
                ", userTrelloToken='" + userTrelloToken + '\'' +
                '}';
    }
}
