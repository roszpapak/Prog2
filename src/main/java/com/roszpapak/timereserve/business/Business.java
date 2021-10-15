package com.roszpapak.timereserve.business;

import com.roszpapak.timereserve.reservation.Reservation;
import com.roszpapak.timereserve.tag.Tag;
import com.roszpapak.timereserve.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    @ManyToMany
    private Set<Tag> tags;
    private String pnumber;
    private LocalTime startTime;
    private LocalTime endTime;
    private int timeInterval;
    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "business",cascade = CascadeType.REMOVE)
    private Set<Reservation> reservations;
}
