package com.roszpapak.timereserve.reservation;

import com.roszpapak.timereserve.business.Business;
import com.roszpapak.timereserve.business.BusinessNotFoundException;
import com.roszpapak.timereserve.business.BusinessRepository;
import com.roszpapak.timereserve.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private ReservationRepository reservationRepository;

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


    @PostMapping("/reservationsave")
    @ResponseBody
    private String reservationSave(@RequestBody ReservationRequest reservationRequest, @AuthenticationPrincipal User user) {
        Optional<Business> businessOptional = businessRepository.findById(reservationRequest.getBusinessId());
        if (businessOptional.isPresent()) {
            Business business = businessRepository.findById(reservationRequest.getBusinessId()).get();
            LocalTime endTime = reservationRequest.getStartTime().plusMinutes(business.getTimeInterval());
            Date date = reservationRequest.getDate();
            Reservation reservation = new Reservation(LocalDate.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault()), reservationRequest.getStartTime(), endTime, business, user);
            reservationRepository.save(reservation);
        }

        return "Hello";
    }


    @PostMapping("/deleteReservation/{id}")
    @ResponseBody
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteById(id);
    }
}
