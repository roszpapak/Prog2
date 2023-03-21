package com.roszpapak.timereserve.DTO;

import com.roszpapak.timereserve.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BusinessDTO {
    private Long id;
    private String name;
    private String address;
    private String pNumber;
    private List<Tag> tags;
}
