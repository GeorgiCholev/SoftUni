package com.example.mobilelewebapp.controllers.impl;

import com.example.mobilelewebapp.controllers.OfferController;
import com.example.mobilelewebapp.models.dtos.OfferAddDto;
import com.example.mobilelewebapp.models.entities.enums.EngineType;
import com.example.mobilelewebapp.models.entities.enums.TransmissionType;
import com.example.mobilelewebapp.models.sessionUser.CurrentUser;
import com.example.mobilelewebapp.services.ModelService;
import com.example.mobilelewebapp.services.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OfferControllerImpl implements OfferController {

    private final ModelService modelService;

    private final CurrentUser currentUser;

    private final OfferService offerService;

    public OfferControllerImpl(ModelService modelService, CurrentUser currentUser, OfferService offerService) {
        this.modelService = modelService;
        this.currentUser = currentUser;
        this.offerService = offerService;
    }

    @ModelAttribute("offerAddDto")
    public void addOfferAddDtoAsAttr(Model model) {
        model.addAttribute("offerAddDto", new OfferAddDto());
    }

    @Override
    public String getOfferAddView(Model model) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        model.addAttribute("modelsByBrandName", this.modelService.getAllModelsByBrandName());
        model.addAttribute("engineTypes", EngineType.labels());
        model.addAttribute("transmissionTypes", TransmissionType.labels());
        return "offer-add";
    }

    @Override
    public String performOfferAdd(OfferAddDto offerAddDto, BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerAddDto", offerAddDto);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.offerAddDto", bindingResult);
            return "redirect:/offer/add";
        }

        offerService.addOffer(offerAddDto, currentUser.getId());
        return "redirect:/";
    }
}
