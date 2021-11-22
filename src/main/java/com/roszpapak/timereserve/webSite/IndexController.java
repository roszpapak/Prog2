package com.roszpapak.timereserve.webSite;

import com.roszpapak.timereserve.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private UserService service;

    @GetMapping("/registration")
    public String getRegistration() {
        return "registration.html";
    }

    @GetMapping("/home")
    public String getHome() {
        return "index.html";
    }


    @GetMapping("/login")
    public String Login() {
        return "login";
    }
}
