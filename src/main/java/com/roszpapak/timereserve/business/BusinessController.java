package com.roszpapak.timereserve.business;

import com.roszpapak.timereserve.reservation.ReservationService;
import com.roszpapak.timereserve.tag.Tag;
import com.roszpapak.timereserve.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BusinessController {

    @Autowired
    BusinessService businessService;
    @Autowired
    ReservationService reservationService;

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

    @GetMapping("/mybusiness")
    public String getMyBusiness(Model model, @AuthenticationPrincipal User user) {

        Business business = businessService.getByUserId(user.getId());
        model.addAttribute("myBusiness", business);
        model.addAttribute("myBusinessReservations", reservationService.getByBusinessId(business.getId()));
        return "mybusiness";
    }



}



