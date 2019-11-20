package com.example.githubclient.ui.event;

import com.example.githubclient.api.model.User;

public class UserEvent {

    private User user;

    public UserEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
