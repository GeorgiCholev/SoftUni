package com.example.pathfinderwebapp.web;

import com.example.pathfinderwebapp.models.dtos.UserRegister;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserRegisterController {

    @ModelAttribute("userRegister")
    public UserRegister initUserRegister() {
        return new UserRegister();
    }

    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }

    //todo: check error class missing??
    @PostMapping("/register")
    public String register(@Valid /*@ModelAttribute("userRegister")*/ UserRegister userRegister,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("userRegister", userRegister);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.userRegister", bindingResult);

            return "redirect:/user/register";
        }

        //todo: register

        return "redirect:/user/login";
    }
}
