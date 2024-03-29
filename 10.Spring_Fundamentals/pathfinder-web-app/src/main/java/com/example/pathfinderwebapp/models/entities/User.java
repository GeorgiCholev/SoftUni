package com.example.pathfinderwebapp.models.entities;

import com.example.pathfinderwebapp.models.dtos.UserRegisterDto;
import com.example.pathfinderwebapp.models.entities.enums.LevelType;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LevelType level;

    @Column(name = "full_name")
    private String fullName;

    @Column
    private Integer age;

    @ManyToMany
    @JoinTable(name = "users_roles",
    joinColumns = @JoinColumn(name = "user_id", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false))
    private Set<Role> roles;

    public User() {
        this.roles = new HashSet<>();
    }

    public User(UserRegisterDto dto, Role userRole) {
        this();
        this.username = dto.getUsername();
        this.password = dto.getPassword();
        this.level = dto.getLevel();
        this.fullName = dto.getFullName();
        this.age = dto.getAge();
        this.roles.add(userRole);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LevelType getLevel() {
        return level;
    }

    public String getFullName() {
        return fullName;
    }

    public Integer getAge() {
        return age;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }
}
