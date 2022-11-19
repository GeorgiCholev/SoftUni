package com.example.gamestore.models;

import com.example.gamestore.models.baseEntity.BaseEntity;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany()
    @JoinTable(name = "orders_games",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    private Set<Game> games;

    public Order() {
    }

    public long getUserId() {
        return user.getId();
    }

    public Set<Game> getGames() {
        return Collections.unmodifiableSet(games);
    }

}
