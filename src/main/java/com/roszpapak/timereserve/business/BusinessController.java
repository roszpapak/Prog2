package com.roszpapak.timereserve.business;

import com.roszpapak.timereserve.DTO.BusinessDTO;
import com.roszpapak.timereserve.exception.FilePathNotValidException;
import com.roszpapak.timereserve.holiday.HolidayService;
import com.roszpapak.timereserve.message.MessageService;
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
    @Autowired
    private HolidayService holidayService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/businesses/findByName")
    @ResponseBody
    public List<BusinessDTO> getBusinesses(@RequestParam String keyword) {
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
    public String getBusinessById(Model model, @PathVariable Long id, @AuthenticationPrincipal User user) {
        model.addAttribute("businessById", businessService.listById(id));
        model.addAttribute("ratings", ratingService.getRatingById(id));
        model.addAttribute("messages", messageService.getChatMessages(user.getId(), businessService.listById(id).getUser().getId()));
        return "business";
    }

    @GetMapping("/mybusiness")
    @Transactional()
    public String getMyBusiness(Model model, @AuthenticationPrincipal User user) {

        Business business = businessService.getByUserId(user.getId());
        model.addAttribute("myBusiness", business);
        model.addAttribute("myBusinessReservations", reservationService.getByBusinessId(business.getId()));
        model.addAttribute("holidays", holidayService.listHolidays(user.getBusiness().getId()));
        model.addAttribute("unreadMessages", messageService.getUnseenMessages(user.getId()));

        return "mybusiness";
    }

    @PostMapping("/editBusiness")
    @ResponseBody
    public void editBusiness(@RequestBody BusinessEditRequest businessEditRequest, @AuthenticationPrincipal User user) {
        businessService.editBusiness(businessEditRequest, user);
    }

    @PostMapping("/changePicture")
    @ResponseBody
    public void changeProfilePicture(@RequestBody MultipartFile file, @AuthenticationPrincipal User user) throws FilePathNotValidException {
        businessService.changeProfilePicture(file, user);
    }


    @GetMapping("/getUserId/business/{id}")
    @ResponseBody
    public String getUserByBusiness(@PathVariable Long id) {
        return businessService.getUserIdByBusiness(id);
    }

    @GetMapping("/getTags")
    @ResponseBody
    public List<String> getTags(@AuthenticationPrincipal User user) {
        return businessService.getTags(user.getBusiness().getId());
    }

}



