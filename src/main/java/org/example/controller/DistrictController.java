package org.example.controller;

import org.example.payload.DistrictDTO;
import org.example.repo.DistrictRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("district")
public class DistrictController {

    @GetMapping
    public ModelAndView get(){
        List<DistrictDTO> districtDTOS= DistrictRepo.findAll();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("districts",districtDTOS);
        modelAndView.setViewName("district");
        return modelAndView;
    }

    @PostMapping("/{id}")
    public String deleteRegion(@PathVariable(name = "id") Integer id){
        DistrictRepo.delete(id);
        return "redirect:/district";
    }
}
