package com.roszpapak.timereserve;

import com.roszpapak.timereserve.business.Business;
import com.roszpapak.timereserve.business.BusinessRepository;
import com.roszpapak.timereserve.tag.Tag;
import com.roszpapak.timereserve.user.User;
import com.roszpapak.timereserve.user.UserRepository;
import com.roszpapak.timereserve.user.UserRole;
import com.roszpapak.timereserve.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TimereserveApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimereserveApplication.class, args);
    }




}
