package com.roszpapak.timereserve.reservation;

import com.roszpapak.timereserve.business.BusinessNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservations/{businessId}/{dateString}")
    @ResponseBody
    public Object getReservations(@PathVariable Long businessId, @PathVariable String dateString) {
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        try {
            return reservationService.getFreeReservationsForDay(businessId, date);
        } catch (BusinessNotFoundException e) {
            return "Business not Found";
        }
    }
}
