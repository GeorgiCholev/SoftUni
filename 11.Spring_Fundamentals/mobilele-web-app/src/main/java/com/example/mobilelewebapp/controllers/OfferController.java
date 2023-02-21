package com.example.mobilelewebapp.controllers;

import com.example.mobilelewebapp.models.dtos.OfferAddDto;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
}
