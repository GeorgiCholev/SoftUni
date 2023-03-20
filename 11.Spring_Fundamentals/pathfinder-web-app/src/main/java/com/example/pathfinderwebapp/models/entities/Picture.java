package com.example.pathfinderwebapp.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String title;

    @Column(unique = true, nullable = false, columnDefinition = "TEXT")
    private String url;

    @ManyToOne(optional = false)
    private User author;

    @ManyToOne(optional = false)
    private Route route;

    public Picture() {
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public User getAuthor() {
        return author;
    }

    public Route getRoute() {
        return route;
    }
}
