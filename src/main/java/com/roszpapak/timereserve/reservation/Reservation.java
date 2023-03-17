package com.roszpapak.timereserve.reservation;

import com.roszpapak.timereserve.business.Business;
import com.roszpapak.timereserve.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate reservationDate;
    private LocalTime startTime;
    private LocalTime endTime;
    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Reservation(LocalDate reservationDate, LocalTime startTime, LocalTime endTime, Business business, User user) {
        this.reservationDate = reservationDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.business = business;
        this.user = user;
    }
}
