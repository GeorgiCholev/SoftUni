package com.example.pathfinderwebapp.web;

import com.example.pathfinderwebapp.models.dtos.UserRegisterDto;
import com.example.pathfinderwebapp.models.entities.enums.LevelType;
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

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
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

        boolean hasBindingErrors = bindingResult.hasErrors();
        if (hasBindingErrors || !userService.register(userRegisterDto)) {
            redirectAttributes.addFlashAttribute("userRegisterDto", userRegisterDto);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.userRegisterDto", bindingResult);
            redirectAttributes.addFlashAttribute("badCredentials", !hasBindingErrors);
            return "redirect:/auth/register";
        }

        return "redirect:/auth/login";
    }
}
