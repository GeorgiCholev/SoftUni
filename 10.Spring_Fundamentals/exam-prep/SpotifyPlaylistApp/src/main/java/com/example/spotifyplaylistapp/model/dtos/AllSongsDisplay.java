package com.example.spotifyplaylistapp.model.dtos;

import java.util.List;

public class AllSongsDisplay {

    private final List<SongDisplay> rockSongs;
    private final List<SongDisplay> jazzSongs;
    private final List<SongDisplay> popSongs;

    public AllSongsDisplay(List<SongDisplay> rockSongs, List<SongDisplay> jazzSongs, List<SongDisplay> popSongs) {
        this.rockSongs = rockSongs;
        this.jazzSongs = jazzSongs;
        this.popSongs = popSongs;
    }

    public List<SongDisplay> getRockSongs() {
        return rockSongs;
    }


    public List<SongDisplay> getJazzSongs() {
        return jazzSongs;
    }


    public List<SongDisplay> getPopSongs() {
        return popSongs;
    }

}
