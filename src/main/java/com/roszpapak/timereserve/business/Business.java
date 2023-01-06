package com.roszpapak.timereserve.business;

import com.roszpapak.timereserve.reservation.Reservation;
import com.roszpapak.timereserve.tag.Tag;
import com.roszpapak.timereserve.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;
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

    @Lob
    private String image;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Tag> tags;
    private String pNumber;
    private LocalTime startTime;
    private LocalTime endTime;
    private int timeInterval;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "business", cascade = CascadeType.REMOVE)
    private Set<Reservation> reservations;

    public Business(String name, String address, List<Tag> tags, String pNumber, LocalTime startTime, LocalTime endTime, int timeInterval, User user) {
        this.name = name;
        this.address = address;
        this.tags = tags;
        this.pNumber = pNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeInterval = timeInterval;
        this.user = user;
    }

    public Business(String name, String address, String image, List<Tag> tags, String pNumber, LocalTime startTime, LocalTime endTime, int timeInterval, User user) {
        this.name = name;
        this.address = address;
        this.image = image;
        this.tags = tags;
        this.pNumber = pNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeInterval = timeInterval;
        this.user = user;
    }
}

