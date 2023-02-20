package com.resellerapp.controller;

import com.resellerapp.config.sessionUser.CurrentUser;
import com.resellerapp.model.dtos.UserLoginDto;
import com.resellerapp.model.dtos.UserRegisterDto;
import com.resellerapp.service.UserService;
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
public class UserManagementController {

    private final UserService userService;

    private final CurrentUser currentUser;

    public UserManagementController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("userRegisterDto")
    public void addUserRegisterDtoAsAttr(Model model) {
        model.addAttribute("userRegisterDto", new UserRegisterDto());
    }

    @GetMapping("/register")
    public String getRegisterView() {
        if (currentUser.isLoggedIn()) {
            return "redirect:/home";
        }

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterDto userRegisterDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userService.register(userRegisterDto)) {
            redirectAttributes.addFlashAttribute("userRegisterDto", userRegisterDto);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.userRegisterDto", bindingResult);
            return "redirect:/user/register";
        }

        return "redirect:/user/login";
    }

    @ModelAttribute("userLoginDto")
    public void addUserLoginDtoAsAttr(Model model) {
        model.addAttribute("userLoginDto", new UserLoginDto());
    }

    @GetMapping("/login")
    public String getLoginView() {
        if (currentUser.isLoggedIn()) {
            return "redirect:/home";
        }

        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDto userLoginDto,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        boolean hasErrors = bindingResult.hasErrors();
        if (hasErrors || !userService.login(userLoginDto)) {
            redirectAttributes.addFlashAttribute("userLoginDto", userLoginDto);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.userLoginDto", bindingResult);
            redirectAttributes.addFlashAttribute("badCredentials", !hasErrors);
            return "redirect:/user/login";
        }

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        if (currentUser.isLoggedIn()) {
            userService.logout();
        }

        return "redirect:/";
    }
}
