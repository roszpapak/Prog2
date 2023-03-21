package com.roszpapak.timereserve.user;

import com.roszpapak.timereserve.message.MessageService;
import com.roszpapak.timereserve.reservation.Reservation;
import com.roszpapak.timereserve.reservation.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;


    @GetMapping("/users")
    public String getUsers(Model model, String keyword) {

        List<User> listUsers = userService.listAll();

        if (keyword != null) {
            model.addAttribute("listUsers", userService.findByKeyWord(keyword));
        } else
            model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/user/{id}")
    public String getBusinessById(Model model, @PathVariable Long id, @AuthenticationPrincipal User user) {
        if (userService.findById(id).isPresent()) {
            model.addAttribute("users", userService.findById(id).get());
            model.addAttribute("messages", messageService.getChatMessages(user.getId(), id));
        }
        return "user";
    }

    @GetMapping("/myprofile")
    @Transactional
    public String getMyProfile(Model model, @AuthenticationPrincipal User myProfile) {
        List<Reservation> reservations = reservationService.getByUserId(myProfile.getId());
        model.addAttribute("myProfile", myProfile);
        model.addAttribute("myReservations", reservations);
        model.addAttribute("unreadMessages", messageService.getUnseenMessages(myProfile.getId()));
        return "myprofile";
    }

    @GetMapping("/findAll")
    @ResponseBody
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("/edituser")
    @ResponseBody
    public String editUser(@RequestBody UserEditRequest userEditRequest, @AuthenticationPrincipal User myProfile) {
        userService.updateUser(userEditRequest, myProfile);
        return "Submit";
    }

    @GetMapping("/getLoggedUser")
    @ResponseBody
    public Long loggedInUser(@AuthenticationPrincipal User user) {
        return user.getId();
    }


}
