package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dtos.UserLogin;
import com.example.spotifyplaylistapp.model.dtos.UserRegister;
import com.example.spotifyplaylistapp.model.sessionModel.CurrentUser;
import com.example.spotifyplaylistapp.service.UserService;
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
@RequestMapping("/user")
public class AuthController {

    private final UserService userService;
    private final CurrentUser currentUser;

    public AuthController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }


    @ModelAttribute("userRegister")
    public void addUserRegisterAsAttr(Model model) {
        model.addAttribute("userRegister", new UserRegister());
    }

    @GetMapping("/register")
    public String getRegisterView() {
        if (currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        return "register";
    }

    @PostMapping("/register")
    public String register( @Valid UserRegister userRegister,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userService.register(userRegister)) {
            redirectAttributes.addFlashAttribute("userRegister", userRegister);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.userRegister", bindingResult);
            return "redirect:/user/register";
        }

        return "redirect:/user/login";
    }

    @ModelAttribute("userLogin")
    public void addUserLoginAsAttr(Model model) {
        model.addAttribute("userLogin", new UserLogin());
    }

    @GetMapping("/login")
    public String getLoginView() {
        if (currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/login")
    public String logIn(@Valid UserLogin userLogin,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            addFlashAttributes(userLogin, bindingResult, redirectAttributes);
            return "redirect:/user/login";
        }

        if (!userService.logIn(userLogin)) {
            addFlashAttributes(userLogin, bindingResult, redirectAttributes);
            return "redirect:/user/login";
        }

        return "redirect:/";
    }

    private void addFlashAttributes(UserLogin userLogin, BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("userLogin", userLogin);
        redirectAttributes.addFlashAttribute
                ("org.springframework.validation.BindingResult.userLogin", bindingResult);
        redirectAttributes.addFlashAttribute("badCredentials", !bindingResult.hasErrors());
    }


    @GetMapping("/logout")
    public String logOut() {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        userService.logOut();
        return "redirect:/";
    }
}
