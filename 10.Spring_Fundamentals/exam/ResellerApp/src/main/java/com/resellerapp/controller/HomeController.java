package com.resellerapp.controller;

import com.resellerapp.config.sessionUser.CurrentUser;
import com.resellerapp.model.dtos.OffersForUser;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.service.OfferService;
import com.resellerapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    private final CurrentUser currentUser;
    private final UserService userService;
    private final OfferService offerService;

    public HomeController(CurrentUser currentUser, UserService userService, OfferService offerService) {
        this.currentUser = currentUser;
        this.userService = userService;
        this.offerService = offerService;
    }

    @GetMapping
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

        OffersForUser offersForCurrentUser = userService.getOffersForUser(currentUser.getId());

        model.addAttribute("offersForUser", offersForCurrentUser);
        model.addAttribute("allOtherOffers",
                offerService.getAllOffersExcept(offersForCurrentUser));

        return "home";
    }

    @GetMapping("/home/remove/{id}")
    public String removeOffer(@PathVariable Long id) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        offerService.remove(id);
        return "redirect:/home";
    }

    @GetMapping("/home/buy-now/{id}")
    public String buyNow(@PathVariable Long id) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        Offer offer = offerService.find(id);
        userService.buyNow(currentUser.getId(), offer);
        return "redirect:/home";
    }
}
