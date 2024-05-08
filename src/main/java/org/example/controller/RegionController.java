package org.example.controller;

import org.example.entity.Country;
import org.example.entity.Region;
import org.example.payload.RegionDTO;
import org.example.repo.CountryRepo;
import org.example.repo.RegionRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("region")
public class RegionController {
    @PostMapping("/addRegion")
    public String addRegion(@ModelAttribute Region region){
        System.out.println(region);
        RegionRepo.saveRegion(region);
        return "redirect:/region";
    }
    @GetMapping
    public ModelAndView get(){
        List<RegionDTO> regionDTOS= RegionRepo.findAllDTORegions();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("regions",regionDTOS);
        modelAndView.setViewName("region");
        return modelAndView;
    }
    @GetMapping("/sendToAddPage")
    public ModelAndView sendAddHtmlPage(){
        ModelAndView modelAndView=new ModelAndView();
        List<Country> regions= CountryRepo.findAll();
        modelAndView.addObject("countries",regions);
        modelAndView.setViewName("addRegion");
        return modelAndView;
    }
    @PostMapping("/{id}")
    public String deleteRegion(@PathVariable(name = "id") Integer id){
        RegionRepo.delete(id);
        return "redirect:/region";
    }
    @GetMapping("/sendEditPage/{id}")
    public ModelAndView sentEditPage(@PathVariable(name = "id") Integer id){
        ModelAndView modelAndView=new ModelAndView();
        Region chosenRegion=RegionRepo.findById(id);
        List<Country> countries=CountryRepo.findAll();
        modelAndView.addObject("chosenRegion",chosenRegion);
        modelAndView.addObject("countries",countries);
        modelAndView.setViewName("editRegion");
        return modelAndView;
    }
    @PostMapping("/edit/{id}")
    public String editRegion(@PathVariable(name = "id") Integer id, @ModelAttribute Region region){
        System.out.println(region);
        RegionRepo.update(id, region);
        return "redirect:/region"; // assuming you want to redirect to the main region page after editing
    }
}
