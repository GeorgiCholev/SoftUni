package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dtos.CurrentUserPlaylist;
import com.example.spotifyplaylistapp.model.sessionModel.CurrentUser;
import com.example.spotifyplaylistapp.service.HomeService;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.util.enums.MusicGenre;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final SongService songService;

    private final UserService userService;

    private final HomeService homeService;

    public HomeController(CurrentUser currentUser, SongService songService, UserService userService, HomeService homeService) {
        this.currentUser = currentUser;
        this.songService = songService;
        this.userService = userService;
        this.homeService = homeService;
    }


    @GetMapping("/")
    public String getIndex() {
        if (currentUser.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String getHomeView(Model model) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        model.addAttribute("songs", this.homeService.getAllSongs());
        CurrentUserPlaylist playlist = this.homeService.getAllSongsFor(currentUser.getId());
        model.addAttribute("currentUserPlaylist", playlist);
        return "home";
    }

    @GetMapping("/home/add/{id}")
    public String addSongToPlaylist(@PathVariable Long id) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        homeService.addSongToUser(id, currentUser.getId());
        return "redirect:/home";
    }

    @GetMapping("/home/remove-all")
    public String removeAllSongForCurrentUser() {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        homeService.removeAllSongsFor(currentUser.getId());
        return "redirect:/home";
    }
}
