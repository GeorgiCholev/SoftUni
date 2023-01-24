package com.example.mobilelewebapp.web;

import com.example.mobilelewebapp.models.dtos.UserRegister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/users")
public class UsersController extends BaseController {

    @GetMapping("/register")
    public ModelAndView getRegisterView(ModelAndView modelAndView) {

        modelAndView.addObject("user", new UserRegister());

        return super.view("auth-register", modelAndView);
    }

    @PostMapping("/register")
    public ModelAndView registerUser(@ModelAttribute UserRegister user) {
        return super.redirect("auth-login");
    }


//        @PostMapping("/register")
//    public ModelAndView postRegister(@ModelAttribute UserRegisterFormDto userRegister) {
//        return super.redirect("auth-login");
//    }

//     public ModelAndView getRegister(ModelAndView modelAndView) {
//        List<UserRoleViewDto> roleServiceAll = this.roleService.getAll();
//
//        modelAndView.addObject("roles", roleServiceAll);
//        modelAndView.addObject("userRegister", new UserRegisterFormDto());
//
//        return super.view("auth-register", modelAndView);
//    }


//    @GetMapping("/register")
//    public ModelAndView showUsersForm() {
//
//        ModelAndView modelAndView = super.view("auth-register");
////        modelAndView.addObject("userRegister", new UserRegister());
////        modelAndView.addAttribute("userRegister", new UserRegister());
//
//        return modelAndView;
//    }


//    	@PostMapping("/greeting")
//	public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
//		model.addAttribute("greeting", greeting);
//		return "result";
//	}

//    @PostMapping("/register")
//    public String formSubmit(@ModelAttribute UserRegister userRegister, ModelAndView modelAndView) {
//        modelAndView.addObject("userRegister", userRegister);
//        Map<String, Object> model = modelAndView.getModel();
////        model.addAttribute("userRegister", userRegister);
//        return "index";
//    }
}
