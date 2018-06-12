
package com.softserve.mosquito.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users_trello")
public class TrelloInfo implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "username")
    private String userTrelloName;

    @Column(name = "access_key")
    private String userTrelloKey;

    @Column(name = "access_token")
    private String userTrelloToken;

    public TrelloInfo() {
    }

    public TrelloInfo(User user, String userTrelloName, String userTrelloKey, String userTrelloToken) {
        this.user = user;
        this.userTrelloName = userTrelloName;
        this.userTrelloKey = userTrelloKey;
        this.userTrelloToken = userTrelloToken;
    }

    public TrelloInfo(Long id, User user, String userTrelloName, String userTrelloKey, String userTrelloToken) {
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
        return "TrelloInfo{" +
                "userId=" + user +
                ", userTrelloName='" + userTrelloName + '\'' +
                ", userTrelloKey='" + userTrelloKey + '\'' +
                ", userTrelloToken='" + userTrelloToken + '\'' +
                '}';
    }

}

