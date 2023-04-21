package com.example.spotifyplaylistapp.model.entity;

import com.example.spotifyplaylistapp.model.dtos.UserRegister;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany
    @JoinTable(name = "users_songs",
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private List<Song> songs = new ArrayList<>();

    public User() {
    }

    public User(UserRegister userRegister) {
        this.username = userRegister.getUsername();
        this.email = userRegister.getEmail();
        this.password = userRegister.getPassword();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public List<Song> getSongs() {
        return songs;
    }
}
