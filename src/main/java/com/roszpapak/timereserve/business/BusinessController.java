package com.roszpapak.timereserve.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @GetMapping("/businesses")
    @ResponseBody
    public List<Business> getBusinesses(Model model, String keyword) {
        List<Business> getBusinessByName = businessService.listByName(keyword);
        List<Business> getAllBusiness = businessService.listAll();
        if (keyword != null) {
            return getBusinessByName;
        } else {
            return getAllBusiness;
        }
    }

    /*@PostMapping("/businesses")
    @ResponseBody
    public List<Business> getBusinessesByName(@RequestBody String keyword, Model model) {
        List<Business> getBusinessByName = businessService.listByName(keyword);
        model.addAttribute("listBusinesses", getBusinessByName);
        return getBusinessByName;
    }*/
}

