package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("main")
public class MainController {
    @GetMapping
    public ModelAndView get(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("user","xasan");
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
