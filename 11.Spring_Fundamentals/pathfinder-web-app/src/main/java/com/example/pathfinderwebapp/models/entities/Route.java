package com.example.pathfinderwebapp.models.entities;

import com.example.pathfinderwebapp.utils.enums.LevelEnum;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "routes")
public class Route extends BaseEntity {

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "gpx_coordinates", columnDefinition = "LONGTEXT")
    private String gpxCoordinates;

    @Enumerated(EnumType.STRING)
    private LevelEnum level;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(name = "video_url")
    private String videoUrl;

    @ManyToOne
    private User author;

    @ManyToMany
    @JoinTable(name = "routes_categories",
            joinColumns = @JoinColumn(name = "route_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id"))
    private Set<Categories> categories;

    public Route() {
        categories = new HashSet<>(4);
    }

    public String getDescription() {
        return description;
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public Long getAuthorId() {
        return author.getId();
    }

    public Set<Categories> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

}
