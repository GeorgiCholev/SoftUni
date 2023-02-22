package com.example.mobilelewebapp.controllers.impl;

import com.example.mobilelewebapp.controllers.OfferController;
import com.example.mobilelewebapp.models.dtos.OfferAddDto;
import com.example.mobilelewebapp.models.dtos.OfferUpdateFormDto;
import com.example.mobilelewebapp.models.dtos.OfferViewDto;
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

    @Override
    public String getOffersAllView(Model model) {
        model.addAttribute("allOffers", this.offerService.getAllOffers().values());
        return "offers";
    }

    @Override
    public String getSelectedOfferDetails(String offerId, Model model) {
        Long offerIdNumber = this.tryToParse(offerId);
        if (offerIdNumber == null) {
            return "redirect:/";
        }
        OfferViewDto offerView = this.offerService.getOfferViewById(offerIdNumber);
        if (offerView == null) {
            return "redirect:/";
        }
        model.addAttribute("offerView", offerView);
        return "details";
    }

    @Override
    public String getUpdateView(String offerId, Model model) {
        Long offerIdNumber = this.tryToParse(offerId);
        if (offerIdNumber == null || !currentUser.isLoggedIn()) {
            return "redirect:/offer/all";
        }

        if (!model.containsAttribute("offerUpdateFormDto")) {
            OfferUpdateFormDto offerUpdateFormDto =
                    this.offerService.getUpdateForm(offerIdNumber, currentUser.getId());
            if (offerUpdateFormDto == null) {
                return "redirect:/offer/all";
            }
            model.addAttribute("offerUpdateFormDto", offerUpdateFormDto);
        }

        model.addAttribute("modelsByBrandName", this.modelService.getAllModelsByBrandName());
        model.addAttribute("engineTypes", EngineType.labels());
        model.addAttribute("transmissionTypes", TransmissionType.labels());
        return "update";
    }

    @Override
    public String performUpdate(String offerId, OfferUpdateFormDto offerUpdateFormDto,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerUpdateFormDto", offerUpdateFormDto);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.offerUpdateFormDto", bindingResult);
            redirectAttributes.addFlashAttribute("id", offerId);
            return "redirect:/offer/update/{id}";
        }

        this.offerService.update(offerUpdateFormDto);
        return "redirect:/offer/all";
    }

    @Override
    public String performDelete(String offerId) {
        Long offerIdNumber = this.tryToParse(offerId);
        if (offerIdNumber == null || !currentUser.isLoggedIn()) {
            return "redirect:/offer/all";
        }
        this.offerService.delete(offerIdNumber, currentUser.getId());
        return "redirect:/offer/all";
    }

    private Long tryToParse(String offerId) {
        try {
            return Long.parseLong(offerId);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
