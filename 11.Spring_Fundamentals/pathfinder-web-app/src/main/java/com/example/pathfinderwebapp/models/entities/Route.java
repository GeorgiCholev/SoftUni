package com.example.pathfinderwebapp.models.entities;

import com.example.pathfinderwebapp.models.entities.enums.LevelType;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "routes")
public class Route extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "gpx_coordinates", columnDefinition = "LONGTEXT")
    private String gpxCoordinates;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LevelType level;

    @Column(name = "video_url")
    private String videoUrl;

    @ManyToOne(optional = false)
    private User author;

    @ManyToMany
    @JoinTable(name = "routes_categories",
            joinColumns = @JoinColumn(name = "route_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "category_id", nullable = false))
    private Set<Category> categories;

    public Route() {
    }

    public String getName() {
        return name;
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public LevelType getLevel() {
        return level;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public User getAuthor() {
        return author;
    }

    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    public void addCategory(Category category) {
        categories.add(category);
    }
}
