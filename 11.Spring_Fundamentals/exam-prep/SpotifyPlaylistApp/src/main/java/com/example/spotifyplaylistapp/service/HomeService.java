package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dtos.AllSongsDisplay;
import com.example.spotifyplaylistapp.model.dtos.CurrentUserPlaylist;
import com.example.spotifyplaylistapp.model.dtos.SongDisplay;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.util.enums.MusicGenre;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeService {

    private final SongService songService;
    private final UserService userService;

    public HomeService(SongService songService, UserService userService) {
        this.songService = songService;
        this.userService = userService;
    }


    public AllSongsDisplay getAllSongs() {
        List<SongDisplay> rockSongs = songService.getAllByStyle(MusicGenre.ROCK);
        List<SongDisplay> jazzSongs = songService.getAllByStyle(MusicGenre.JAZZ);
        List<SongDisplay> popSongs = songService.getAllByStyle(MusicGenre.POP);

        return new AllSongsDisplay(rockSongs, jazzSongs, popSongs);
    }

    @Transactional
    public CurrentUserPlaylist getAllSongsFor(Long id) {
        List<SongDisplay> playlist = this.userService.getSongsFor(id)
                .stream()
                .map(SongDisplay::new)
                .collect(Collectors.toList());

        return new CurrentUserPlaylist(playlist);
    }

    @Transactional
    public void addSongToUser(Long songId, Long userId) {
        Song song = this.songService.getSongById(songId);
        this.userService.addSongTo(userId, song);
    }

    @Transactional
    public void removeAllSongsFor(Long userId) {
        this.userService.removeAllSongs(userId);
    }
}
