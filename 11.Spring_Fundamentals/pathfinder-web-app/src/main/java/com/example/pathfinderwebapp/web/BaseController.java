package com.example.pathfinderwebapp.web;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {

    public ModelAndView addViewName(String viewName, ModelAndView modelAndView) {
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    public ModelAndView addViewName(String viewName) {
        return addViewName(viewName, new ModelAndView());
    }
}
