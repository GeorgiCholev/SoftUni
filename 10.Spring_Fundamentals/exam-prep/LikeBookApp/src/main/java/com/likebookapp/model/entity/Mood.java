package com.likebookapp.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "moods")
public class Mood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private MoodName moodName;

    private String description;

    public Mood() {
    }

    public Mood(MoodName moodName) {
        this.moodName = moodName;
    }

    public Long getId() {
        return id;
    }

    public MoodName getMoodName() {
        return moodName;
    }

    public String getDescription() {
        return description;
    }
}
