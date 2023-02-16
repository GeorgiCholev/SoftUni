package com.example.spotifyplaylistapp.model.entity;

import com.example.spotifyplaylistapp.model.dtos.SongAdd;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String performer;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer duration;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @ManyToOne(optional = false)
    private Style style;

    public Song() {
    }

    public Song(SongAdd songAdd, Style style) {
        this.performer = songAdd.getPerformer();
        this.title = songAdd.getTitle();
        this.duration = songAdd.getDuration();
        this.releaseDate = songAdd.getReleasedDate();
        this.style = style;
    }


    public Long getId() {
        return id;
    }

    public String getPerformer() {
        return performer;
    }

    public String getTitle() {
        return title;
    }

    public Integer getDuration() {
        return duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Style getStyle() {
        return style;
    }

    @Override
    public String toString() {
        return performer + " - " + title + " (" + duration + " min)";
    }
}
