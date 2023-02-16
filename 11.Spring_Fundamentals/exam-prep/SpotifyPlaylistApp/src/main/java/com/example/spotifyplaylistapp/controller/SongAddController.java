package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dtos.SongAdd;
import com.example.spotifyplaylistapp.model.sessionModel.CurrentUser;
import com.example.spotifyplaylistapp.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/song/add")
public class SongAddController {

    private final SongService songService;
    private final CurrentUser currentUser;

    public SongAddController(SongService songService, CurrentUser currentUser) {
        this.songService = songService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("songAdd")
    public void addSongAddAsAttr(Model model) {
        model.addAttribute("songAdd", new SongAdd());
    }

    @GetMapping
    public String getSongAddView() {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        return "song-add";
    }

    @PostMapping
    public String addSong(@Valid SongAdd songAdd,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.songService.addSong(songAdd)) {
            redirectAttributes.addFlashAttribute("songAdd", songAdd);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.songAdd", bindingResult);

            return "redirect:/song/add";
        }

        return "redirect:/";
    }
}
