package com.example.mobilelewebapp.web;

import com.example.mobilelewebapp.models.dtos.UserRegister;
import com.example.mobilelewebapp.services.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private final RoleService roleService;

    public UserController(RoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping("/register")
    public ModelAndView getRegisterView(ModelAndView modelAndView) {

        modelAndView.addObject("roleTypes", roleService.getAllRoleTypes());

        return super.view("auth-register", modelAndView);

    }

    @PostMapping("/register")
    public ModelAndView registerUser(UserRegister userRegister) {
        return super.redirect("auth-login");
    }

}
