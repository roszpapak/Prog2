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

import javax.transaction.Transactional;
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
    @Transactional
    public String getMyProfile(Model model, @AuthenticationPrincipal User myProfile) {
        List<Reservation> reservations = reservationService.getByUserId(myProfile.getId());
        model.addAttribute("myProfile", myProfile);
        model.addAttribute("myReservations", reservations);
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


}
