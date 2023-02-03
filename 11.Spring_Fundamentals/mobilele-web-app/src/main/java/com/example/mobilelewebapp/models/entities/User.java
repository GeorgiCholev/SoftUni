package com.example.mobilelewebapp.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String username;

    @Column
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToMany
    @JoinTable(name = "users_roles",
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @Column(name = "image_url")
    private String imageUrl;
    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime modified;

    @OneToMany(mappedBy = "seller")
    private List<Offer> offers;

    public User() {
        this.roles = new ArrayList<>();
        this.offers = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public int getPasswordHashCode() {
        return password.hashCode();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isActive() {
        return isActive;
    }

    public List<Role> getRoles() {
        return Collections.unmodifiableList(roles);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCreated() {
        return created.toString();
    }

    public String getModified() {
        return modified.toString();
    }

    public List<Offer> getOffers() {
        return Collections.unmodifiableList(offers);
    }
}
