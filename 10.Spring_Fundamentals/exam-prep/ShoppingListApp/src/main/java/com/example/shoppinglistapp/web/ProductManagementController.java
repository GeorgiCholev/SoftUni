package com.example.shoppinglistapp.web;

import com.example.shoppinglistapp.models.dtos.ProductAddDTO;
import com.example.shoppinglistapp.models.sessionUser.CurrentUser;
import com.example.shoppinglistapp.services.ProductService;
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
@RequestMapping("/product")
public class ProductManagementController {

    private final ProductService productService;
    private final CurrentUser currentUser;

    public ProductManagementController(ProductService productService, CurrentUser currentUser) {
        this.productService = productService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("productAddDTO")
    public void addProductAddDtoAsAttr(Model model) {
        model.addAttribute("productAddDTO", new ProductAddDTO());
    }

    @GetMapping("/add")
    public String getProductAddView() {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/user/login";
        }

        return "product-add";
    }

    @PostMapping("/add")
    public String addProduct(@Valid ProductAddDTO productAddDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAddDTO", productAddDTO);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.productAddDTO", bindingResult);
            return "redirect:/product/add";
        }

        productService.addProduct(productAddDTO);
        return "redirect:/home";
    }
}
