package com.roszpapak.timereserve.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @GetMapping("/businesses/findByName")
    @ResponseBody
    public List<Business> getBusinesses(@RequestParam String keyword) {
        if (keyword != null) {
            return businessService.listByName(keyword.toLowerCase());
        } else {
            return businessService.listAll();
        }
    }

    @GetMapping("/businesses")
    public String getBusinesses(Model model) {
        model.addAttribute("listBusinesses", businessService.listAll());
        return "businesses";
    }

    @GetMapping("/businesses/{id}")
    public String getBusinessById(Model model, @PathVariable Long id) {
        model.addAttribute("businessById", businessService.listById(id));
        return "business";
    }

}



