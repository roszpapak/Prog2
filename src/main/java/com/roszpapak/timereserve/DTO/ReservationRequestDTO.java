package com.roszpapak.timereserve.DTO;

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
public class ReservationRequestDTO {

    private Date date;
    @NotNull
    private Long businessId;
    private LocalTime startTime;

}
