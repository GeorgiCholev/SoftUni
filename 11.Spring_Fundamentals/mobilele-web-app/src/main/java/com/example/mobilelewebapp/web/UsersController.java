package com.example.mobilelewebapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UsersController extends BaseController {

    @GetMapping("/register")
    public ModelAndView registerView() {
        return super.view("auth-register");
    }
}
