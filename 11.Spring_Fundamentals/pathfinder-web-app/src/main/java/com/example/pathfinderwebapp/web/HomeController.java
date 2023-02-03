package com.example.pathfinderwebapp.web;

import com.example.pathfinderwebapp.services.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController {

    private final RouteService routeService;

    public HomeController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public ModelAndView getHome(ModelAndView modelAndView) {
        modelAndView.addObject("mostCommentedRoute", this.routeService.getMostCommentedRoute());
        return super.addViewName("index", modelAndView);
    }
}
