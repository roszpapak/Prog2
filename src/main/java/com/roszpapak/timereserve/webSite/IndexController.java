package com.roszpapak.timereserve.webSite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {


    @GetMapping("/logout")
    public String logout() {
        return "redirect:login?logout";
    }

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

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/")
    public String start() {
        return "index";
    }
}
