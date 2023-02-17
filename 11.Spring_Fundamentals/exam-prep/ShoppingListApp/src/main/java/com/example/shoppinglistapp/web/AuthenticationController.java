package com.example.shoppinglistapp.web;

import com.example.shoppinglistapp.models.dtos.UserLoginDTO;
import com.example.shoppinglistapp.models.dtos.UserRegisterDTO;
import com.example.shoppinglistapp.models.sessionUser.CurrentUser;
import com.example.shoppinglistapp.services.AuthenticationService;
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
    public void addUserRegisterDtoAsAttr(Model model) {
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
    public void addUserLoginDtoAsAttr(Model model) {
        model.addAttribute("userLoginDTO", new UserLoginDTO());
    }

    @GetMapping("/login")
    public String getLoginView() {
        if (currentUser.isLoggedIn()) {
            return "redirect:/home";
        }

        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDTO userLoginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        boolean hasErrors = bindingResult.hasErrors();
        if (hasErrors || !authenticationService.login(userLoginDTO)) {
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.userLoginDTO", bindingResult);
            redirectAttributes.addFlashAttribute("badCredentials", !hasErrors);
            return "redirect:/user/login";
        }

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        authenticationService.logout();
        return "redirect:/";
    }
}
