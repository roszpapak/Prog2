package com.roszpapak.timereserve.business;

import com.roszpapak.timereserve.tag.Tag;
import lombok.*;

import java.time.LocalTime;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class BusinessEditRequest {

    private String name;
    private String phone;
    private String address;
    private List<Tag> tags;
    private LocalTime startTime;
    private LocalTime endTime;


}
