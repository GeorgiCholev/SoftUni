package com.likebookapp.controller;

import com.likebookapp.model.sessionEntity.CurrentUser;
import com.likebookapp.service.HomeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final HomeService homeService;

    public HomeController(CurrentUser currentUser, HomeService homeService) {
        this.currentUser = currentUser;
        this.homeService = homeService;
    }

    @GetMapping("/")
    public String getIndexView() {
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

        model.addAttribute("postsForCurrentUser",
                homeService.getAllPostsForUser(currentUser.getId(), currentUser.getUsername()));

        model.addAttribute("postsNotByCurrentUser",
                homeService.getAllPostsNotFor(currentUser.getId(), currentUser.getUsername()));

        return "home";
    }
}
