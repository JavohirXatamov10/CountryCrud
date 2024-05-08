package org.example.controller;

import org.example.entity.Country;
import org.example.repo.CountryRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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



    @GetMapping("/add")
    public ModelAndView addHtm(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("AddCountry");

        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editHtml(@PathVariable(name = "id") Integer id){
        ModelAndView modelAndView=new ModelAndView();
        Country country=CountryRepo.findById(id);
        modelAndView.addObject("countryItem",country);
        modelAndView.setViewName("editCountry");
        return modelAndView;
    }

    @PostMapping("/add")
    public String addCountry(@ModelAttribute Country country){
        CountryRepo.addCountry(country);
        return "redirect:/country";
    }



    @PostMapping("/editCountry/{id}") // Ensure to specify the HTTP method as POST
    public String editCountry(@PathVariable(name = "id") Integer id,@ModelAttribute Country country){
        System.out.println(country);
        CountryRepo.update(id,country);

        return "redirect:/country";
    }
}
