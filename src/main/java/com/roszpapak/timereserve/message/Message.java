package com.roszpapak.timereserve.message;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long fromId;
    private Long toId;
    private String messageContent;
    private LocalDateTime receivedDate;
    private Boolean seen = false;
}
