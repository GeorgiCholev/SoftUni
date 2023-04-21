package com.likebookapp.controller;

import com.likebookapp.model.dto.UserLoginDTO;
import com.likebookapp.model.dto.UserRegisterDTO;
import com.likebookapp.model.sessionEntity.CurrentUser;
import com.likebookapp.service.AuthenticationService;
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
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final CurrentUser currentUser;

    public AuthenticationController(AuthenticationService authenticationService, CurrentUser currentUser) {
        this.authenticationService = authenticationService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("userRegisterDTO")
    public void addUserRegisterDTOToModel(Model model) {
        model.addAttribute("userRegisterDTO", new UserRegisterDTO());
    }

    @GetMapping("/register")
    public String getRegisterView() {
        if (currentUser.isLoggedIn()) {
            return "redirect:/home";
        }

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterDTO userRegisterDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !authenticationService.register(userRegisterDTO)) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.userRegisterDTO", bindingResult);
            return "redirect:/user/register";
        }

        return "redirect:/user/login";
    }

    @ModelAttribute("userLoginDTO")
    public void addUserLoginDTOToModel(Model model) {
        model.addAttribute("userLoginDTO", new UserLoginDTO());
    }

    @GetMapping("/login")
    public String getLoginView() {
        if (currentUser.isLoggedIn()) {
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("login")
    public String logIn(@Valid UserLoginDTO userLoginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        boolean bindingResultErrors;
        if ((bindingResultErrors = bindingResult.hasErrors()) || !authenticationService.login(userLoginDTO)) {
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("badCredentials", !bindingResultErrors);
            if (bindingResultErrors) {
                redirectAttributes.addFlashAttribute
                        ("org.springframework.validation.BindingResult.userLoginDTO", bindingResult);
            }
            return "redirect:/user/login";
        }

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logPut() {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        authenticationService.logout();
        return "redirect:/";
    }
}
