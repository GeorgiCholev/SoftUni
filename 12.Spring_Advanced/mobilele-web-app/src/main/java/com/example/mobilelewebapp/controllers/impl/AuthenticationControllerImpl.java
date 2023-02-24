package com.example.mobilelewebapp.controllers.impl;

import com.example.mobilelewebapp.controllers.AuthenticationController;
import com.example.mobilelewebapp.models.dtos.UserLoginDto;
import com.example.mobilelewebapp.validation.UniqueFieldError;
import com.example.mobilelewebapp.models.dtos.UserRegisterDto;
import com.example.mobilelewebapp.models.sessionUser.CurrentUser;
import com.example.mobilelewebapp.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthenticationControllerImpl implements AuthenticationController {

    private final CurrentUser currentUser;
    private final UserService userService;

    public AuthenticationControllerImpl(CurrentUser currentUser, UserService userService) {
        this.currentUser = currentUser;
        this.userService = userService;
    }

    @ModelAttribute("userRegisterDto")
    public void addUserRegisterDtoAsAttr(Model model) {
        model.addAttribute("userRegisterDto", new UserRegisterDto());
    }

    @Override
    public String getRegisterView() {
        if (currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        return "auth-register";
    }

    @Override
    public String performRegister(UserRegisterDto userRegisterDto,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterDto", userRegisterDto);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.userRegisterDto", bindingResult);
            return "redirect:/auth/register";
        }

        UniqueFieldError uniqueFieldError = this.userService.uniqueFieldErrorOrRegister(userRegisterDto);
        if (uniqueFieldError != null) {
            redirectAttributes.addFlashAttribute("userRegisterDto", userRegisterDto);
            redirectAttributes.addFlashAttribute("uniqueFieldError", uniqueFieldError);
            return "redirect:/auth/register";
        }

        return "redirect:/auth/login";
    }

    @ModelAttribute("userLoginDto")
    public void addUserLoginDtoAsAttr(Model model) {
        model.addAttribute("userLoginDto", new UserLoginDto());
    }

    @Override
    public String getLoginView() {
        if (currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        return "auth-login";
    }

    @Override
    public String performLogin(UserLoginDto userLoginDto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        boolean bindingErrors = bindingResult.hasErrors();
        if (bindingErrors || !userService.login(userLoginDto)) {
            redirectAttributes.addFlashAttribute("userLoginDto", userLoginDto);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.userLoginDto", bindingResult);
            redirectAttributes.addFlashAttribute("badCredentials", !bindingErrors);
            return "redirect:/auth/login";
        }

        return "redirect:/";
    }

    @Override
    public String performLogout() {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        userService.logout();
        return "redirect:/";
    }
}
