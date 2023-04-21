package com.example.spotifyplaylistapp.model.sessionModel;

import com.example.spotifyplaylistapp.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

    private Long id;

    private String username;

    public void becomes(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public void clear() {
        this.id = null;
        this.username = null;
    }

    public boolean isLoggedIn() {
        return id != null;
    }

    public Long getId() {
        return id;
    }
}
