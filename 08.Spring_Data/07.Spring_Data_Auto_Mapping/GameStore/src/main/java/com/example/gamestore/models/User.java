package com.example.gamestore.models;

import com.example.gamestore.models.baseEntity.BaseEntity;
import com.example.gamestore.models.dto.RegisterUserDTO;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "full_name", unique = true)
    private String fullName;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @ManyToMany
    @JoinTable(name = "users_games",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    private Set<Game> games;

    public User() {
        this.games = new HashSet<>();
    }

    public User(RegisterUserDTO userRegisterDTO) {
        this();
        this.email = userRegisterDTO.getEmail();
        this.password = userRegisterDTO.getPassword();
        this.fullName = userRegisterDTO.getFullName();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Set<Game> getGames() {
        return Collections.unmodifiableSet(this.games);
    }
}
