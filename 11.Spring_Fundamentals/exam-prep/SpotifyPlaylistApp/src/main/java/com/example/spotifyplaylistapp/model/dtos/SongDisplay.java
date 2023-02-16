package com.example.spotifyplaylistapp.model.dtos;

import com.example.spotifyplaylistapp.model.entity.Song;

public class SongDisplay {

    private long id;
    private String performer;

    private String title;

    private String duration;

    public SongDisplay(Song song) {
        this.id = song.getId();
        this.performer = song.getPerformer();
        this.title = song.getTitle();
        this.duration = parseDuration(song.getDuration());
    }

    private String parseDuration(Integer duration) {
        int minutes = duration / 60;
        int seconds = duration % 60;
        return minutes + ":" + seconds;
    }

    public int parseDuration() {
        String[] minutesAndSeconds = this.duration.split(":");
        return Integer.parseInt(minutesAndSeconds[0]) * 60 + Integer.parseInt(minutesAndSeconds[1]);
    }

    public long getId() {
        return id;
    }

    public String getPerformer() {
        return performer;
    }

    public String getTitle() {
        return title;
    }

    public String getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return performer + " - " + title + " (" + duration + " min)";
    }
}
