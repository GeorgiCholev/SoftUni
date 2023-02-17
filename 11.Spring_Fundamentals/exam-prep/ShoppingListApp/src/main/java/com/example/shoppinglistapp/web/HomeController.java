package com.example.shoppinglistapp.web;

import com.example.shoppinglistapp.models.sessionUser.CurrentUser;
import com.example.shoppinglistapp.services.HomeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        model.addAttribute("productsByCategoryName", homeService.getProductsByCategoryName());
        return "home";
    }

    @GetMapping("/home/buy/{id}")
    public String buyProduct(@PathVariable Long id) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        homeService.buyProduct(id);
        return "redirect:/home";
    }

    @GetMapping("/home/buy-all")
    public String buyAllProducts() {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        homeService.buyAllProducts();
        return "redirect:/home";
    }
}
