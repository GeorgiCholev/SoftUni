package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import com.example.spotifyplaylistapp.util.enums.MusicGenre;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SeedService {

    private final StyleRepository styleRepository;

    public SeedService(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }


    public void seedStyles() {
        if (styleRepository.count() != 0) {
            return;
        }

        List<Style> allStyles = Arrays.stream(MusicGenre.values())
                .map(Style::new)
                .toList();

        styleRepository.saveAll(allStyles);
    }
}
