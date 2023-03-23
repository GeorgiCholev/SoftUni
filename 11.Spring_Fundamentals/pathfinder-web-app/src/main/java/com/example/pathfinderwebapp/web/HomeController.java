package com.example.pathfinderwebapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Controller
@RequestMapping("/home")
public class HomeController {


    @GetMapping
    public String getHomeView(Model model) {
        return "index";
    }

    private String a(Function<Integer, String> a) {
        Map<Integer, String> b =new HashMap<>();
        b.replaceAll();
        for (Map.Entry<Integer, String> integerStringEntry : b.entrySet()) {

        }
    }
}
