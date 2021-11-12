package com.roszpapak.timereserve.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserController {

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
        model.addAttribute("myProfile", myProfile);
        return "myProfile";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditMyProfile(@PathVariable("id") Long id, Model model) {

        User user = userService.get(id);
        model.addAttribute("updatedMyProfile", user);
        return "myProfile";
    }
}
