package com.example.spotifyplaylistapp.init;

import com.example.spotifyplaylistapp.service.SeedService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StylesInit implements CommandLineRunner {

    private final SeedService seedService;

    public StylesInit(SeedService seedService) {
        this.seedService = seedService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedService.seedStyles();
    }
}
