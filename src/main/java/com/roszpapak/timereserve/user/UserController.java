package com.roszpapak.timereserve.user;

import com.roszpapak.timereserve.reservation.Reservation;
import com.roszpapak.timereserve.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public String getUsers(Model model, String keyword) {

        List<User> listUsers = userService.listAll();

        if (keyword != null) {
            model.addAttribute("listUsers", userService.findByKeyWord(keyword));
        } else
            model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/myprofile")
    public String getMyProfile(Model model, @AuthenticationPrincipal User myProfile) {
        List<Reservation> reservations = reservationService.getByUserId(myProfile.getId());
        model.addAttribute("myProfile", myProfile);
        model.addAttribute("myReservations", reservations);
        return "myprofile";
    }

    @PostMapping("/edituser")
    @ResponseBody
    public String editUser(@RequestBody UserEditRequest userEditRequest, @AuthenticationPrincipal User myProfile) {

        myProfile.setFirstName(userEditRequest.getFirstName());
        myProfile.setLastName(userEditRequest.getLastName());
        if (userEditRequest.getPassword() != null) {
            myProfile.setPassword(bCryptPasswordEncoder.encode(userEditRequest.getPassword()));
        }
        userRepository.save(myProfile);
        return "Submit";
    }


}
