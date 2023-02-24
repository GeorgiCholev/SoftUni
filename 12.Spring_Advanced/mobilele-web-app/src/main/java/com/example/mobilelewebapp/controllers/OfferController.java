package com.example.mobilelewebapp.controllers;

import com.example.mobilelewebapp.models.dtos.OfferAddDto;
import com.example.mobilelewebapp.models.dtos.OfferUpdateFormDto;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/offer")
public interface OfferController {

    @GetMapping("/add")
    String getOfferAddView(Model model);

    @PostMapping("/add")
    String performOfferAdd(@Valid OfferAddDto offerAddDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes);

    @GetMapping("/all")
    String getOffersAllView(Model model);

    @GetMapping("/details/{id}")
    String getSelectedOfferDetails(@PathVariable("id") String offerId, Model model);

    @GetMapping("/update/{id}")
    String getUpdateView(@PathVariable("id") String offerId, Model model);

    @PostMapping("/update/{id}")
    String performUpdate(@PathVariable("id") String offerId,
                         @Valid OfferUpdateFormDto offerUpdateFormDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes);

    @GetMapping("/delete/{id}")
    String performDelete(@PathVariable("id") String offerId);
}
