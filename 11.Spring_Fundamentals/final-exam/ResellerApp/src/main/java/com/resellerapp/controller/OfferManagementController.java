package com.resellerapp.controller;

import com.resellerapp.config.sessionUser.CurrentUser;
import com.resellerapp.model.dtos.OfferAddDto;
import com.resellerapp.service.OfferService;
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
@RequestMapping("/offer")
public class OfferManagementController {

    private final OfferService offerService;

    private final CurrentUser currentUser;

    public OfferManagementController(OfferService offerService, CurrentUser currentUser) {
        this.offerService = offerService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("offerAddDto")
    public void addProductAddDtoAsAttr(Model model) {
        model.addAttribute("offerAddDto", new OfferAddDto());
    }

    @GetMapping("/add")
    public String getOfferAddView() {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/user/login";
        }

        return "offer-add";
    }

    @PostMapping("/add")
    public String addOffer(@Valid OfferAddDto offerAddDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerAddDto", offerAddDto);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.offerAddDto", bindingResult);
            return "redirect:/offer/add";
        }

        offerService.addOffer(currentUser.getId(), offerAddDto);
        return "redirect:/home";
    }
}
