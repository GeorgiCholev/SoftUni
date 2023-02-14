package com.example.mobilelewebapp.web;

import com.example.mobilelewebapp.models.dtos.UserLogin;
import com.example.mobilelewebapp.services.UserService;
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
@RequestMapping("/user")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userLogin")
    public void initUserLogin(Model model) {
        model.addAttribute("userLogin", new UserLogin());
    }

    @GetMapping("/login")
    public String getLoginView() {
        return "auth-login";
    }

    @GetMapping("/logout")
    public String logout() {
        userService.logOut();
        return "redirect:/";
    }

    @PostMapping("/login")
    public String logInUser(@Valid UserLogin userLogin,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("badCredentials", true);
            redirectAttributes.addFlashAttribute
                    ("defaultMessage", bindingResult.getAllErrors().get(0).getDefaultMessage());

            return "redirect:/user/login";
        }

        userService.logIn(userLogin);
        return "redirect:/";
    }
}
