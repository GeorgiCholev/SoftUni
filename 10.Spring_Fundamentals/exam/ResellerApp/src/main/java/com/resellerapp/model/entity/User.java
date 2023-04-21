package com.resellerapp.model.entity;

import com.resellerapp.model.dtos.UserRegisterDto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "seller")
    private Set<Offer> offers;

    @OneToMany
    @JoinTable(name = "users_bought_offers",
            inverseJoinColumns = @JoinColumn(name = "bought_offer_id"))
    private Set<Offer> boughtOffers;

    public User() {
        this.offers = new HashSet<>();
        this.boughtOffers = new HashSet<>();
    }

    public User(UserRegisterDto dto) {
        this();
        this.username = dto.getUsername();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
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

    public Set<Offer> getOffers() {
        return offers;
    }

    public Set<Offer> getBoughtOffers() {
        return boughtOffers;
    }
}
