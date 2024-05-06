package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("district")
public class DistrictController {
    @GetMapping
    public ModelAndView get(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("district","uchtepa");
        modelAndView.setViewName("district");
        return modelAndView;
    }
}
