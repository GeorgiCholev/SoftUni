package com.example.mobilelewebapp.web;

import com.example.mobilelewebapp.models.dtos.UserRegister;
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
public class RegisterController extends BaseController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userRegister")
    public void initUserRegister(Model model) {
        model.addAttribute("userRegister", new UserRegister());
    }

    @GetMapping("/register")
    public String getRegisterView() {
        return "auth-register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserRegister userRegister,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegister", userRegister);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.userRegister", bindingResult);
            return "redirect:/user/register";
        }

        userService.register(userRegister);

        return "redirect:/user/login";
    }

}
