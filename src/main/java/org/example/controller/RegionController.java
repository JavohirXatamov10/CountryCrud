package org.example.controller;

import org.example.payload.RegionDTO;
import org.example.repo.RegionRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("region")
public class RegionController {
    @GetMapping
    public ModelAndView get(){
        List<RegionDTO> regionDTOS= RegionRepo.findAllDTORegions();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("regions",regionDTOS);
        modelAndView.setViewName("region");
        return modelAndView;
    }

    @PostMapping("/{id}")
    public String deleteRegion(@PathVariable(name = "id") Integer id){
        RegionRepo.delete(id);
        return "redirect:/region";
    }

}
