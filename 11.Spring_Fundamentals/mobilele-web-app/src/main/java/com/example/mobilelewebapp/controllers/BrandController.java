package com.example.mobilelewebapp.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/brand")
public interface BrandController {

    @GetMapping("/all")
    String getAllBrandsView(Model model);
}
