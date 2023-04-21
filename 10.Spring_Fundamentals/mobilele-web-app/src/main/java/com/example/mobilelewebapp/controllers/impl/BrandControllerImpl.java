package com.example.mobilelewebapp.controllers.impl;

import com.example.mobilelewebapp.controllers.BrandController;
import com.example.mobilelewebapp.services.ModelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class BrandControllerImpl implements BrandController {

    private final ModelService modelService;

    public BrandControllerImpl(ModelService modelService) {
        this.modelService = modelService;
    }


    @Override
    public String getAllBrandsView(Model model) {
        model.addAttribute("modelsByBrandName", this.modelService.getAllModelsByBrandName());
        return "brands";
    }
}
