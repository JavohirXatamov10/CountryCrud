package org.example.controller;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.Region;
import org.example.repo.RegionRepo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("findRegion")
public class FindRegion {
    @GetMapping("/getRegions/{id}")//rescontroller bolishi kerakyoki get param
    public void getRegions(@PathVariable(name = "id") Integer countryId, HttpServletResponse response) {
        try {
        List<Region> regions= RegionRepo.findByCountryId(countryId);
        Gson gson=new Gson();
        String json = gson.toJson(regions);
        response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            writer.print(json);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
