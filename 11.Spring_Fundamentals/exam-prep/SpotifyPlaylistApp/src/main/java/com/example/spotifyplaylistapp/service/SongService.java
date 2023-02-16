package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dtos.SongAdd;
import com.example.spotifyplaylistapp.model.dtos.SongDisplay;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import com.example.spotifyplaylistapp.util.enums.MusicGenre;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final StyleRepository styleRepository;

    public SongService(SongRepository songRepository, StyleRepository styleRepository) {
        this.songRepository = songRepository;
        this.styleRepository = styleRepository;
    }

    public boolean addSong(SongAdd songAdd) {
        MusicGenre name = songAdd.getStyle();
        Optional<Style> optStyle = styleRepository.findByName(name);

        if (optStyle.isEmpty()) {
            return false;
        }

        songRepository.save(new Song(songAdd, optStyle.get()));
        return true;
    }

    public List<SongDisplay> getAllByStyle(MusicGenre style) {
        Optional<Style> optStyle = this.styleRepository.findByName(style);
         return songRepository.findAllByStyle(optStyle.get())
                 .stream()
                 .map(SongDisplay::new)
                 .collect(Collectors.toList());
    }

    public Song getSongById(Long id) {
        return songRepository.findById(id).get();
    }
}
