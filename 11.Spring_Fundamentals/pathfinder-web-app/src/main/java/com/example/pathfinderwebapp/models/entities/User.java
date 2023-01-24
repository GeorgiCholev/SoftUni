package com.example.pathfinderwebapp.models.entities;

import com.example.pathfinderwebapp.utils.enums.LevelEnum;
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

    @Column(name = "full_name", nullable = false)
    private String fullName;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private LevelEnum level;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles;

    public User() {
        roles = new HashSet<>(3);
    }

    public String getUsername() {
        return username;
    }

    public int getPasswordHashCode() {
        return password.hashCode();
    }

    public String getFullName() {
        return fullName;
    }

    public Integer getAge() {
        return age;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

}
