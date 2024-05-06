package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("region")
public class RegionController {
    @GetMapping
    public ModelAndView get(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("region","Jizzakh");
        modelAndView.setViewName("region");
        return modelAndView;
    }

}
