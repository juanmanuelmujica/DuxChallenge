package com.duxchallenge.apifootballteams.data.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {
    @EmbeddedId
    private UserId userId;

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }
}
