package com.roszpapak.timereserve.reservation;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalTime;
import java.util.Date;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ReservationRequest {

    private Date date;
    @NotNull
    private Long businessId;
    private LocalTime startTime;

}
