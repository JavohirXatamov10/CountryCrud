package org.example.controller;

import org.example.entity.Country;
import org.example.repo.CountryRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("country")
public class CountryController {
    @GetMapping
    public ModelAndView get(){
        ModelAndView modelAndView=new ModelAndView();
        List<Country> countries= CountryRepo.findAll();
        modelAndView.addObject("countries",countries);
        modelAndView.setViewName("country");
        return modelAndView;
    }
    @PostMapping("/{id}")
    public String deleteCountry(@PathVariable(name = "id") Integer id){
        CountryRepo.delete(id);
        return "redirect:/country";
    }
}
