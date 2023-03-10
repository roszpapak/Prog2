package com.roszpapak.timereserve.business;

import com.roszpapak.timereserve.DTO.HolidayRequestDTO;
import com.roszpapak.timereserve.rating.RatingService;
import com.roszpapak.timereserve.reservation.ReservationService;
import com.roszpapak.timereserve.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class BusinessController {

    @Autowired
    private BusinessService businessService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private RatingService ratingService;

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
        model.addAttribute("ratings", ratingService.getRatingById(id));
        return "business";
    }

    @GetMapping("/mybusiness")
    @Transactional()
    public String getMyBusiness(Model model, @AuthenticationPrincipal User user) {

        Business business = businessService.getByUserId(user.getId());
        model.addAttribute("myBusiness", business);
        model.addAttribute("myBusinessReservations", reservationService.getByBusinessId(business.getId()));
        model.addAttribute("holidays", businessService.listHolidays(user.getBusiness().getId()));


        return "mybusiness";
    }

    @PostMapping("/editBusiness")
    @ResponseBody
    public void editBusiness(@RequestBody BusinessEditRequest businessEditRequest, @AuthenticationPrincipal User user) {
        businessService.editBusiness(businessEditRequest, user);
    }

    @PostMapping("/changePicture")
    @ResponseBody
    public void changeProfilePicture(@RequestBody MultipartFile file, @AuthenticationPrincipal User user) {
        businessService.changeProfilePicutre(file, user);
    }

    @PostMapping("/takeHoliday")
    @ResponseBody
    public void takeHoliday(@RequestBody HolidayRequestDTO holidayRequestDTO,
                            @AuthenticationPrincipal User user) {
        businessService.takeHoliday(holidayRequestDTO, user);
    }


}



