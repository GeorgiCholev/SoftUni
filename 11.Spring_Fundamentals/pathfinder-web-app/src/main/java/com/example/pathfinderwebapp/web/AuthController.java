package com.example.pathfinderwebapp.web;

import com.example.pathfinderwebapp.models.dtos.UserLoginDto;
import com.example.pathfinderwebapp.models.dtos.UserRegisterDto;
import com.example.pathfinderwebapp.models.entities.enums.LevelType;
import com.example.pathfinderwebapp.models.sessionUser.SessionUser;
import com.example.pathfinderwebapp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final SessionUser sessionUser;

    public AuthController(UserService userService, SessionUser sessionUser) {
        this.userService = userService;
        this.sessionUser = sessionUser;
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        if (sessionUser.isLoggedIn()) {
            return "redirect:/home";
        }
        model.addAttribute("levels", LevelType.values());
        return "register";
    }

    @ModelAttribute("userRegisterDto")
    public void addUserRegisterDtoAsAttr(Model model) {
        model.addAttribute("userRegisterDto", new UserRegisterDto());
    }

    @PostMapping("/register")
    public String postRegisterRequest(@Valid UserRegisterDto userRegisterDto,
                                      BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userService.register(userRegisterDto)) {
            return redirectWithFlashAttributes("userRegisterDto", userRegisterDto,
                    "/auth/register", bindingResult, redirectAttributes);
        }

        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String getLoginForm() {
        if (sessionUser.isLoggedIn()) {
            return "redirect:/home";
        }
        return "login";
    }

    @ModelAttribute("userLoginDto")
    public void addUserLoginDtoAsAttr(Model model) {
        model.addAttribute("userLoginDto", new UserLoginDto());
    }

    @PostMapping("/login")
    public String postLoginRequest(@Valid UserLoginDto userLoginDto,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userService.login(userLoginDto)) {
            return redirectWithFlashAttributes("userLoginDto", userLoginDto,
                    "/auth/login", bindingResult, redirectAttributes);
        }

        return "redirect:/home";
    }

    @GetMapping("logout")
    public String getLogout() {
        if (sessionUser.isLoggedIn()) {
            userService.logout();
        }

        return "redirect:/";
    }


    private <T> String redirectWithFlashAttributes(String dtoName, T dto, String redirectAddress,
                                                   BindingResult bindingResult,
                                                   RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(dtoName, dto);
        redirectAttributes.addFlashAttribute
                ("org.springframework.validation.BindingResult." + dtoName, bindingResult);
        redirectAttributes.addFlashAttribute("badCredentials", !bindingResult.hasErrors());
        return "redirect:" + redirectAddress;
    }
}
