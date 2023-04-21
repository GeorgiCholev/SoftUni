package com.example.spotifyplaylistapp.model.entity;

import com.example.spotifyplaylistapp.util.enums.MusicGenre;

import javax.persistence.*;

@Entity
@Table(name = "styles")
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private MusicGenre name;

    @Column(columnDefinition = "text")
    private String description;

    public Style(MusicGenre name) {
        this.name = name;
    }

    public Style() {
    }

    public Long getId() {
        return id;
    }

    public MusicGenre getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
