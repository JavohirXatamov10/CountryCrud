package org.example.controller;

import org.example.entity.Country;
import org.example.entity.District;
import org.example.entity.Region;
import org.example.payload.DistrictDTO;
import org.example.repo.CountryRepo;
import org.example.repo.DistrictRepo;
import org.example.repo.RegionRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping("/add")
    public ModelAndView addDistrict(){
        ModelAndView modelAndView=new ModelAndView();
        List<Country> countries=CountryRepo.findAll();
        List<Region> regions=RegionRepo.findAll();
        modelAndView.addObject("countries", countries);
        modelAndView.addObject("regions", regions);
        modelAndView.setViewName("addDistrict");
        return modelAndView;
    }
    @GetMapping ("/sentToEditPage/{id}")
    public ModelAndView editRegion(@PathVariable(name = "id") Integer id, @ModelAttribute Region region){
            ModelAndView modelAndView=new ModelAndView();
            District chosenDistrict=DistrictRepo.findById(id);
            List<Region> regions= RegionRepo.findAll();
            modelAndView.addObject("chosenDistrict",chosenDistrict);
            modelAndView.addObject("regions",regions);
            modelAndView.setViewName("editDistrict");
            return modelAndView;
    }
    @PostMapping("/edit/{id}")
    public String editDistrict(@PathVariable(name = "id") Integer id, @ModelAttribute District district){
        DistrictRepo.update(id, district);
        return "redirect:/district"; // assuming you want to redirect to the main region page after editing
    }
    @PostMapping("/{id}")
    public String deleteRegion(@PathVariable(name = "id") Integer id){
        DistrictRepo.delete(id);
        return "redirect:/district";
    }
    @PostMapping("/addDistrict")
    public String addDistrict(@ModelAttribute District district) {
        DistrictRepo.save(district);
        return "redirect:/district";
    }


}
