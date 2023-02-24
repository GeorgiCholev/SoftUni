package com.example.mobilelewebapp.controllers;

import com.example.mobilelewebapp.models.dtos.UserLoginDto;
import com.example.mobilelewebapp.models.dtos.UserRegisterDto;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/auth")
public interface AuthenticationController {

    @GetMapping("/register")
    String getRegisterView();

    @PostMapping("/register")
    String performRegister(@Valid UserRegisterDto userRegisterDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes);

    @GetMapping("/login")
    String getLoginView();

    @PostMapping("/login")
    String performLogin(@Valid UserLoginDto userLoginDto,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes);

    @GetMapping("/logout")
    String performLogout();
}
