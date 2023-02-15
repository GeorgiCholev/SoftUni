package com.example.spotifyplaylistapp.model.dtos;

import java.util.List;

public class CurrentUserPlaylist {

    private final List<SongDisplay> playlist;

    public CurrentUserPlaylist(List<SongDisplay> playlist) {
        this.playlist = playlist;
    }

    public List<SongDisplay> getPlaylist() {
        return playlist;
    }

    public String getTotalDuration() {
        int totalDuration = playlist.stream()
                .mapToInt(SongDisplay::parseDuration)
                .sum();

        return (totalDuration / 60) + ":" + (totalDuration % 60);
    }

}
