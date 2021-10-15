package com.roszpapak.timereserve.reservation;

import com.roszpapak.timereserve.business.Business;
import com.roszpapak.timereserve.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @ManyToOne
    @JoinColumn(name = "business_id",nullable = false)
    private Business business;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

}
