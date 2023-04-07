package com.roszpapak.timereserve.business;

import lombok.*;

import java.time.LocalTime;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class BusinessEditRequest {

    private String name;
    private String phone;
    private String address;
    private String tags;
    private LocalTime startTime;
    private LocalTime endTime;


}
