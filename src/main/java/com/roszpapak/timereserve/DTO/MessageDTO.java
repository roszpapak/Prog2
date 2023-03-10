package com.roszpapak.timereserve.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageDTO implements Serializable {
    private Long fromId;
    private Long toId;
    private String message;
}
